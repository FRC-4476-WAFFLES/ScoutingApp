package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GameId1TM extends AppCompatActivity {
    Bitmap bitmap;
    private static final String TAG = "GameID1TM";
    Typeface CooperBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_tm);
        final GlobalData app_data = (GlobalData)getApplicationContext();
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");

        TextView title = (TextView)findViewById(R.id.tmTitleTextView);
        title.setTypeface(CooperBlack);

        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        String content = app_data.genContentString();
        QRGEncoder qrgEncoder = new QRGEncoder(content, null, QRGContents.Type.TEXT, smallerDimension);
        final ImageView qrImage = (ImageView)findViewById(R.id.qr_image);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v(TAG, e.toString());
        }
        Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                app_data.setGame_state(getString(R.string.pregame));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);

                //clean up the match data
                app_data.reset_all_match_data();
                Log.d(TAG, "starting GameId1.java");

                startActivity(startIntent);
            }
        });
    }
}
