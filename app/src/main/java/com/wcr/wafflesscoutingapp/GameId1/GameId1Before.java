package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.MainActivity;
import com.wcr.wafflesscoutingapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class GameId1Before extends AppCompatActivity {
    Typeface CooperBlack;
    String DriverStation = "";
    String StartOnLevel2 = "";
    String StartingPosition = "";
    //TODO: make buttons save data on press


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_before);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        final GlobalData app_data = (GlobalData)getApplicationContext();
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        //Team Number
        final TextView teamNumberEditText = (TextView) findViewById(R.id.teamNumberTextView);
        teamNumberEditText.setText(getTeamNumber(app_data.getLocalBluetoothName(), app_data.match));

        //Match Number
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        matchNumberEditText.setText("" + app_data.match);

        //Driver Station buttons
        DriverStation = "";
        final Button blue1Button = (Button)findViewById(R.id.blue1Button);
        blue1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(0);
                DriverStation = "Blue 1";
            }
        });
        final Button blue2Button = (Button)findViewById(R.id.blue2Button);
        blue2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(1);
                DriverStation = "Blue 2";
            }
        });
        final Button blue3Button = (Button)findViewById(R.id.blue3Button);
        blue3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(2);
                DriverStation = "Blue 3";
            }
        });
        final Button red1Button = (Button)findViewById(R.id.red1Button);
        red1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(3);
                DriverStation = "Red 1";
            }
        });
        final Button red2Button = (Button)findViewById(R.id.red2Button);
        red2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(4);
                DriverStation = "Red 2";
            }
        });
        final Button red3Button = (Button)findViewById(R.id.red3Button);
        red3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(5);
                DriverStation = "Red 3";
            }
        });

        // level 2 check box
        StartOnLevel2 = "1";
        final CheckBox lvl2 = (CheckBox) findViewById(R.id.startPosCheckBox);
        if(lvl2.isChecked()) {
            StartOnLevel2 = "2";
        }

        //Starting position
        final Button leftButton = (Button)findViewById(R.id.leftButton);
        final Button centerButton = (Button)findViewById(R.id.centerButton);
        final Button rightButton = (Button)findViewById(R.id.rightButton);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerButton.setBackgroundColor(getResources().getColor(R.color.grey));
                rightButton.setBackgroundColor(getResources().getColor(R.color.grey));
                leftButton.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                StartingPosition = "l";
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerButton.setBackgroundColor(getResources().getColor(R.color.grey));
                leftButton.setBackgroundColor(getResources().getColor(R.color.grey));
                rightButton.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                StartingPosition = "r";
            }
        });
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightButton.setBackgroundColor(getResources().getColor(R.color.grey));
                leftButton.setBackgroundColor(getResources().getColor(R.color.grey));
                centerButton.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                StartingPosition = "c";
            }
        });



        //continue button
        final Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: make sure to save the data
                String TeamNumber = teamNumberEditText.getText().toString();
                String MatchNumber = matchNumberEditText.getText().toString();

                //assign data to array
                app_data.setMatchDataId(0, TeamNumber, GameId1Before.this);
                app_data.setMatchDataId(1, MatchNumber, GameId1Before.this);
                app_data.match = Integer.parseInt(MatchNumber);
                app_data.setMatchDataId(44, app_data.getApp_config(0) + "." + app_data.getApp_config(1), GameId1Before.this);
                if(DriverStation.charAt(0) == 'B'){
                    app_data.setMatchDataId(2, "b", GameId1Before.this);
                }else if(DriverStation.charAt(0) == 'R'){
                    app_data.setMatchDataId(2, "r", GameId1Before.this);
                }
                app_data.setMatchDataId(3, StartingPosition, GameId1Before.this);
                app_data.setMatchDataId(4, StartOnLevel2, GameId1Before.this);


                //make sure to check all fields are filled
                if(!DriverStation.equals("") && !StartOnLevel2.equals("") && !StartingPosition.equals("") && !TeamNumber.equals("") && !MatchNumber.equals("")) {
                    app_data.setGame_state(getString(R.string.sandstorm));
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else{
                    Toast.makeText(GameId1Before.this, "please make sure all fields are filled...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void DSLocBtn(int id){
        Button buttons[] = new Button[6];
        buttons[0] = (Button)findViewById(R.id.blue1Button);//0
        buttons[1] = (Button)findViewById(R.id.blue2Button);//1
        buttons[2] = (Button)findViewById(R.id.blue3Button);//2
        buttons[3] = (Button)findViewById(R.id.red1Button);//3
        buttons[4] = (Button)findViewById(R.id.red2Button);//4
        buttons[5] = (Button)findViewById(R.id.red3Button);//5
        for(int i=0; i< buttons.length; i++){
            if(id != i){
                buttons[i].setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
        if(id<3) {
            buttons[id].setBackgroundColor(getResources().getColor(R.color.blue));
        }else{
            buttons[id].setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    private String getTeamNumber(String Name, int match){
        //Name should be the name of the device for use as the scout driver station position
        //|->usually from bluetooth.
        String team = "0000";
        String[] names = {"R1", "R2", "R3", "B1", "B2", "B3"};
        if(Arrays.asList(names).contains(Name)){
            try {
                InputStreamReader is = new InputStreamReader(getAssets().open("MatchSchedule.csv"));

                BufferedReader reader = new BufferedReader(is);
                reader.readLine();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(",");
                    if(row[0].replaceAll('"', '') == ""+match){
                        team = row[Arrays.asList(names).indexOf(Name)].replaceAll('"', '');
                        return team;
                    }
                }
            }catch (IOException e){
                Log.e("Game1IdBefore.Java", "" + e.getMessage());
            }
        }else{
            Toast.makeText(this, "DeviceName Set incorrectly", Toast.LENGTH_SHORT);
            Log.e("Game1IdBefore.Java", "Bluetooth device name set incorrectly");
        }
        return team;
    }
}

