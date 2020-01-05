package com.wcr.wafflesscoutingapp.GameId2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.wcr.wafflesscoutingapp.BuildConfig;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScoutPicActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1001;
    static final String TAG = "ScoutPicActivity";
    String currentPhotoPath;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GlobalData app_data = (GlobalData)getApplicationContext();

        setContentView(R.layout.activity_scout_pic);
        final Button takePicture = (Button)findViewById(R.id.nextButton);
        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(app_data.isWriteExternalStorageGranted()){
//                    openCameraIntent();
//                    openCamera();
                    openBackCamera();
                }
            }
        });
    }

    private void openCamera() {
        File Root = Environment.getExternalStorageDirectory();
        File Dir = new File(Root.getAbsoluteFile() + "/WafflesScoutingAppData");
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture.jpg");
        values.put(MediaStore.Images.Media.DESCRIPTION, "its a new pic");
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Dir.toString());
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera Intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1001){
            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile() + "/WafflesScoutingAppData");

            if (!Dir.exists()) {
                Dir.mkdir();
            }
            File file = new File(Dir, "4476.jpg");
            try (FileOutputStream out = new FileOutputStream(file)) {
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
                Log.d(TAG, "saved??");
                // PNG is a lossless format, the compression factor (100) is ignored
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    String imageFilePath;
    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }
    private static final int REQUEST_CAPTURE_IMAGE = 100;

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName(), photoFile);
                Uri photoURI = Uri.fromFile(photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    private String pictureImagePath = "";
    private void openBackCamera() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "4476" + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
        File file = new File(pictureImagePath);
        Uri outputFileUri = Uri.fromFile(file);
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, 1);
    }
}
