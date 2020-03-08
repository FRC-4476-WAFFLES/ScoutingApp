package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.wcr.wafflesscoutingapp.ScoutData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameId1Before extends AppCompatActivity {
    Typeface CooperBlack;
    String DriverStation = "Me3rther";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_before);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        final GlobalData app_data = (GlobalData)getApplicationContext();
        TextView titleTextView = (TextView)findViewById(R.id.beforeTitleTextView);
        titleTextView.setTypeface(CooperBlack);

        //Team Number
        final TextView teamNumberEditText = (TextView) findViewById(R.id.teamNumberTextView);
        teamNumberEditText.setText(getTeamNumber(app_data.getLocalBluetoothName(), app_data.match));

        //figure out what driverstation we are looking at on this device
        DriverStation = app_data.getLocalBluetoothName();


        //Match Number
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        matchNumberEditText.setText("" + app_data.match);
        final TextView scoutInformerTextView = findViewById(R.id.scoutInformerTextView);
        scoutInformerTextView.setTypeface(CooperBlack);
        scoutInformerTextView.setText("You Are Scouting Team " + getTeamNumber(DriverStation, app_data.match) +" In Driverstation " + expandName(DriverStation));

        matchNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = matchNumberEditText.getText().toString();
                if(!text.equals("") && text.length()<6 && !text.equals("0") && !text.equals("00") && !text.equals("000") && !text.equals("0000") && !text.equals("00000")) {
                    app_data.match = Integer.parseInt(matchNumberEditText.getText().toString());
                    teamNumberEditText.setText(getTeamNumber(DriverStation, app_data.match));
                    scoutInformerTextView.setText("You Are Scouting Team " + getTeamNumber(DriverStation, app_data.match) +" In Driverstation " + expandName(DriverStation));
                }else{
                    matchNumberEditText.setText("1");
                }
            }
        });

        if(DriverStation.charAt(0) == 'B'){
            scoutInformerTextView.setBackgroundColor(getResources().getColor(R.color.blue));
        }else{
            scoutInformerTextView.setBackgroundColor(getResources().getColor(R.color.red));
        }

        //Starting position
        final Button leftButton = (Button)findViewById(R.id.leftButton);
        final Button centerButton = (Button)findViewById(R.id.centerButton);
        final Button rightButton = (Button)findViewById(R.id.rightButton);
        final List<Button> startPosButtons = Arrays.asList(leftButton, centerButton, rightButton);
        //Load Last
        String tmp = "" + app_data.getMatchDataId(4);
        if(!tmp.equals("")){
            if(tmp.equals("1")){
                UpdateTriColour(startPosButtons, 0);
            }else if(tmp.equals("r")){
                UpdateTriColour(startPosButtons, 2);
            }else if(tmp.equals("c")){
                UpdateTriColour(startPosButtons, 1);
            }
        }
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTriColour(startPosButtons, 0);
                app_data.setMatchDataId(4, "left", GameId1Before.this);
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTriColour(startPosButtons, 2);
                app_data.setMatchDataId(4, "right", GameId1Before.this);
            }
        });
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateTriColour(startPosButtons, 1);
                app_data.setMatchDataId(4, "center", GameId1Before.this);
            }
        });

        final Button changeScout = (Button)findViewById(R.id.changeScoutButton);
        changeScout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ScoutData.class);
                startActivity(startIntent);
            }
        });

        //continue button
        final Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TeamNumber = teamNumberEditText.getText().toString();
                String MatchNumber = matchNumberEditText.getText().toString();

                //assign data to array
                app_data.setMatchDataId(0, TeamNumber, GameId1Before.this);
                app_data.setMatchDataId(1, MatchNumber, GameId1Before.this);
                app_data.match = Integer.parseInt(MatchNumber);
                //set the scout name
                app_data.setMatchDataId(3, app_data.getApp_config(0) + "." + app_data.getApp_config(1), GameId1Before.this);
                Log.e("GameId1Before.java", DriverStation);
                //determine the driverstation about to be scouted
                if(DriverStation.charAt(0) == 'B'){
                    app_data.setMatchDataId(2, DriverStation, GameId1Before.this);
                }else if(DriverStation.charAt(0) == 'R'){
                    app_data.setMatchDataId(2, DriverStation, GameId1Before.this);
                }else {
                    DriverStation = "";
                }

                String startPosit = app_data.getMatchDataId(4);
                if(startPosit == null){
                    startPosit = "";
                }
                //make sure to check all fields are filled
                if(!DriverStation.equals("") && !startPosit.equals("") && !TeamNumber.equals("") && !MatchNumber.equals("") && !MatchNumber.equals("0")) {
                    app_data.setGame_state(getString(R.string.sandstorm));
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else{
                    Toast.makeText(GameId1Before.this, "please make sure all fields are filled...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private String expandName(String shortened){
        if(shortened.equals("R1")){
            return "Red 1";
        }else if(shortened.equals("R2")){
            return "Red 2";
        }else if(shortened.equals("R3")){
            return "Red 3";
        }else if(shortened.equals("B1")){
            return "Blue 1";
        }else if(shortened.equals("B2")){
            return "Blue 2";
        }else if(shortened.equals("B3")){
            return "Blue 3";
        }else{
            return "Device Name Set Incorrectly";
        }
    }

    private String getTeamNumber(String Name, int match){
        //Name should be the name of the device for use as the scout driver station position
        //|->usually from bluetooth.
        String team = "0000";
        String[] names = {"R1", "R2", "R3", "B1", "B2", "B3"};
        final GlobalData app_data = (GlobalData)getApplicationContext();
        if(Arrays.asList(names).contains(Name)){
            try {
                InputStreamReader is = new InputStreamReader(getAssets().open("MatchSchedule.csv"));

                BufferedReader reader = new BufferedReader(is);
                reader.readLine();
                String line;
                app_data.number_of_matches = 0;
                while ((line = reader.readLine()) != null) {
                    app_data.number_of_matches += 1;
                    //TODO: chack that the match exists???
                    String[] row = line.split(",");
                    if(row[0].replaceAll("\"", "").equals( ""+match)){
                        team = row[Arrays.asList(names).indexOf(Name) + 1].replaceAll("\"", "");
                    }
                }
                return team;
                //TODO: make sure this doesn't crash
            }catch (IOException e){
                Log.e("Game1IdBefore.Java", "" + e.getMessage());
            }
        }else{
            Toast.makeText(this, "DeviceName Set incorrectly", Toast.LENGTH_SHORT);
            Log.e("Game1IdBefore.Java", "Bluetooth device name set incorrectly");
            Log.e("GameId1Before.java", "device name: " + Name);
        }
        return team;
    }

    private void UpdateTriColour(List<Button> buttonList, int clicked){
        for(int i = 0; i<3; i++){
            Button tmp = buttonList.get(i);
            if(i == clicked){
                tmp.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            }else{
                tmp.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
    }
}

