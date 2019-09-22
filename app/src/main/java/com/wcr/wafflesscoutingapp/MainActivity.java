package com.wcr.wafflesscoutingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // importing fonts: https://www.youtube.com/watch?v=fB17m3kX-go\
    public static final int writeExternal = 1;
    public static final int readExternal = 2;
    public static final int coarseLocation = 3;

    Typeface CooperBlack;
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        TextView textView2020 = (TextView)findViewById(R.id.textView0);
        textView2020.setTypeface(CooperBlack);
        TextView textView2019 = (TextView)findViewById(R.id.textView1);
        textView2019.setTypeface(CooperBlack);
        TextView textView2018 = (TextView)findViewById(R.id.textView2);
        textView2018.setTypeface(CooperBlack);
        TextView textView2017 = (TextView)findViewById(R.id.textView3);
        textView2017.setTypeface(CooperBlack);
        TextView textGrid = (TextView)findViewById(R.id.textGrid);
        textGrid.setTypeface(CooperBlack);
        //config
        final GlobalData app_data = (GlobalData)getApplicationContext();
        app_data.load_config(this);
        app_data.setApp_config(this, 3, Settings.Secure.getString(getContentResolver(), "bluetooth_name"));

        //permissions
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1234);

        //Set Event
        setSingleEvent(mainGrid);

    }


    private void setSingleEvent(GridLayout mainGrid) {
        final GlobalData app_data = (GlobalData) getApplicationContext();
        //Loop all child items of Main Grid
        for(int i = 0; i<mainGrid.getChildCount(); i++){
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        cardView.setBackgroundColor(Color.parseColor("#FF6F00"));
                    }else{
                        cardView.setCardBackgroundColor(Color.parseColor("FFFFFF"));
                    }
                    //replace with start new activity code
                    Toast.makeText(MainActivity.this, "Clicked at index " + finalI, Toast.LENGTH_SHORT).show();

                    Intent startIntent = new Intent(getApplicationContext(), ScoutData.class);
                    //show how to pass information to another activity
                    app_data.set_Game_index(finalI);
                    startActivity(startIntent);
                }
            });

        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "The app has to save items to the external storage", Toast.LENGTH_SHORT).show();
                }
            }
            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "The app has to read items from the external storage", Toast.LENGTH_SHORT).show();
                }
            }
            case 3: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "The app has to access coarse location for bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
            case 4: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "The app has to access fine location for bluetooth", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}
