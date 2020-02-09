package com.wcr.wafflesscoutingapp.GameId2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.TestLooperManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.wcr.wafflesscoutingapp.BuildConfig;

import com.wcr.wafflesscoutingapp.GameId1.GameId1;
import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScoutPicActivity extends AppCompatActivity {
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    static final String TAG = "ScoutPicActivity";
    private String teamNumber = "";
    private TextView teamNumbersComplete;
    GlobalData app_data;
    Typeface CooperBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_pic);
        //Config
        app_data = (GlobalData)getApplicationContext();
        CooperBlack = Typeface.createFromAsset(ScoutPicActivity.this.getAssets(), "fonts/CooperFiveOpti-Black.otf");

        //TEXT
        final TextView getTeamNumber = (TextView)findViewById(R.id.picTitleTextView);
        final TextView takenPicturesTextView = (TextView)findViewById(R.id.takenPicturesTextView);
        takenPicturesTextView.setTypeface(CooperBlack);
        teamNumbersComplete = (TextView)findViewById(R.id.teamNumberTextView);
        teamNumbersComplete.setTypeface(CooperBlack);
        teamNumbersComplete.setText(app_data.getApp_config(4));

        //edit text
        final EditText teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);


        final Button takePicture = (Button)findViewById(R.id.nextButton);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(app_data.isWriteExternalStorageGranted()){
                    teamNumber = teamNumberEditText.getText().toString();
                    if(!teamNumber.equals("")){
                        openCameraIntent();
                    }else{
                        Toast.makeText(ScoutPicActivity.this, "Please Enter A Team Number...", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

        final Button deletePictures = (Button)findViewById(R.id.deletePicturesButton);
        deletePictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                try {
                    FileUtils.cleanDirectory(dir);
                }catch (IOException ex){
                    Log.e(TAG, ex.getMessage());
                    Log.e(TAG, "unable to clean images directory");
                }
                app_data.setApp_config(ScoutPicActivity.this, 4, "");
                teamNumbersComplete.setText(app_data.getApp_config(4));
            }
        });
    }

    String imageFilePath;
    private File createImageFile() throws IOException {
        String imageFileName = teamNumber;
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = new File(storageDir, imageFileName+".jpg");
        image.createNewFile();
        imageFilePath = image.getAbsolutePath();
        return image;
    }


    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e(TAG, "Error occurred while creating the temp file File");
                Log.e(TAG, ex.getMessage()+"");
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.wcr.fileprovider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int ResultCode, @Nullable Intent data){
        if(ResultCode == RESULT_OK && !teamNumber.equals("")){
            app_data.setApp_config(this, 4, app_data.getApp_config(4) + teamNumber + ",");
            teamNumbersComplete.setText(app_data.getApp_config(4));
        }
    }
}
