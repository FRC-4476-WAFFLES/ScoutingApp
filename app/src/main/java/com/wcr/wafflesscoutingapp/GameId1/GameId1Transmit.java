package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import java.io.File;

public class GameId1Transmit extends AppCompatActivity {
    Typeface CooperBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_transmit);

        //import global data
        final GlobalData app_data = (GlobalData)getApplicationContext();
        sendFile(app_data.current_file);

        //title
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFile(app_data.current_file);
            }
        });

        Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                app_data.setGame_state(getString(R.string.pregame));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });

    }
    public void sendFile(String fileName){
//        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
//        File manualFile = new File(dir, "/" + fileName);
//        Uri uri = Uri.fromFile(manualFile);
//        String type = "text/plain";
//
//        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//        sharingIntent.setType(type);
//        sharingIntent.setClassName("com.android.bluetooth", "com.android.bluetooth.opp.BluetoothOppLauncherActivity");
//        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
//        startActivity(sharingIntent);
    }
}
