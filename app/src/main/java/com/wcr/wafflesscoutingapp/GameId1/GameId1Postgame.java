package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId1Postgame extends AppCompatActivity {
    Typeface CooperBlack;
    String ClimbLvl1 = "0";
    String ClimbLvl2 = "0";
    String ClimbLvl3 = "0";
    String DidNotTry = "0";
    String AllianceScore = "0";
    String AllianceFouls = "0";
    String ScoutComments = "0";
    boolean ClimbSet = false;
    //TODO: make buttons save data on press



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_Postgame);

        //global data import
        final GlobalData app_data = (GlobalData)getApplicationContext();

        //title
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        // climb buttons Lvl 1
        {
            final Button L1YesButton = (Button)findViewById(R.id.L1YesButton);
            L1YesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClimbLvl1 = "Yes";
                    ClimbSet = true;
                    ClimbBtn(0);
                }
            });

        }

        {
            final Button L1FailButton = (Button)findViewById(R.id.L1FailButton);
            L1FailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClimbLvl1 = "Fail";
                    ClimbSet = true;
                    ClimbBtn(1);
                }
            });

        }

        // climb buttons Lvl 2
        {
            final Button L2YesButton = (Button)findViewById(R.id.L2YesButton);
            L2YesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClimbLvl2 = "Yes";
                    ClimbSet = true;
                    ClimbBtn(2);
                }
            });

        }

        {
            final Button L2FailButton = (Button)findViewById(R.id.L2FailButton);
            L2FailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClimbLvl2 = "Fail";
                    ClimbSet = true;
                    ClimbBtn(3);
                }
            });

        }

        // climb buttons Lvl 3
        {
            final Button L3YesButton = (Button)findViewById(R.id.L3YesButton);
            L3YesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClimbLvl3 = "Yes";
                    ClimbSet = true;
                    ClimbBtn(4);
                }
            });

        }

        {
            final Button L3FailButton = (Button)findViewById(R.id.L3FailButton);
            L3FailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClimbLvl3 = "Fail";
                    ClimbSet = true;
                    ClimbBtn(5);
                }
            });

        }

        //did not attempt climb
        {
            final Button didNotTryButton = (Button)findViewById(R.id.didNotTryButton);
            didNotTryButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DidNotTry = "1";
                    ClimbSet = true;
                    ClimbBtn(6);
                }
            });

        }

        //edit texts
        final EditText allianceScoreEditText = (EditText)findViewById(R.id.allianceScoreEditText);
        final EditText allianceFoulsEditText = (EditText)findViewById(R.id.allianceFoulsEditText);
        final EditText commentsEditText = (EditText)findViewById(R.id.commentsEditText);


        //continue button
        final Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: make sure to save the data
                AllianceScore = allianceScoreEditText.getText().toString();
                app_data.setMatchDataId(41, AllianceScore, GameId1Postgame.this);
                AllianceFouls = allianceFoulsEditText.getText().toString();
                app_data.setMatchDataId(42, AllianceFouls, GameId1Postgame.this);
                ScoutComments = commentsEditText.getText().toString();
                app_data.setMatchDataId(43, ScoutComments, GameId1Postgame.this);

                if(ClimbLvl1.equals("Yes")){
                    app_data.setMatchDataId(34, "1", GameId1Postgame.this);
                    app_data.setMatchDataId(35, "0", GameId1Postgame.this);
                }else if(ClimbLvl1.equals("Fail")){
                    app_data.setMatchDataId(34, "0", GameId1Postgame.this);
                    app_data.setMatchDataId(35, "1", GameId1Postgame.this);
                }else{
                    app_data.setMatchDataId(34, "0", GameId1Postgame.this);
                    app_data.setMatchDataId(35, "0", GameId1Postgame.this);
                }

                if(ClimbLvl2.equals("Yes")){
                    app_data.setMatchDataId(36, "1", GameId1Postgame.this);
                    app_data.setMatchDataId(37, "0", GameId1Postgame.this);
                }else if(ClimbLvl2.equals("Fail")){
                    app_data.setMatchDataId(36, "0", GameId1Postgame.this);
                    app_data.setMatchDataId(37, "1", GameId1Postgame.this);
                }else{
                    app_data.setMatchDataId(36, "0", GameId1Postgame.this);
                    app_data.setMatchDataId(37, "0", GameId1Postgame.this);
                }

                if(ClimbLvl3.equals("Yes")){
                    app_data.setMatchDataId(38, "1", GameId1Postgame.this);
                    app_data.setMatchDataId(39, "0", GameId1Postgame.this);
                }else if(ClimbLvl3.equals("Fail")){
                    app_data.setMatchDataId(38, "0", GameId1Postgame.this);
                    app_data.setMatchDataId(39, "1", GameId1Postgame.this);
                }else{
                    app_data.setMatchDataId(38, "0", GameId1Postgame.this);
                    app_data.setMatchDataId(39, "0", GameId1Postgame.this);
                }

                app_data.setMatchDataId(40, DidNotTry, GameId1Postgame.this);

                //make sure to check all fields are filled
                if(!AllianceScore.equals("") && !AllianceFouls.equals("") && !ScoutComments.equals("")) {
                    app_data.setGame_state(getString(R.string.transmit));
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else{
                    Toast.makeText(GameId1Postgame.this, "please make sure all fields are filled...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ClimbBtn(int id){
        Button buttons[] = new Button[7];
        buttons[0] = (Button)findViewById(R.id.L1YesButton);//0
        buttons[1] = (Button)findViewById(R.id.L1FailButton);//1
        buttons[2] = (Button)findViewById(R.id.L2YesButton);//2
        buttons[3] = (Button)findViewById(R.id.L2FailButton);//3
        buttons[4] = (Button)findViewById(R.id.L3YesButton);//4
        buttons[5] = (Button)findViewById(R.id.L3FailButton);//5
        buttons[6] = (Button)findViewById(R.id.didNotTryButton);//6

        for(int i=0; i< buttons.length; i++){
            if(id != i){
                buttons[i].setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
        if(id == 0 ||id == 2 ||id == 4) {
            buttons[id].setBackgroundColor(getResources().getColor(R.color.green));
        }else if(id == 1 ||id == 3 ||id == 5){
            buttons[id].setBackgroundColor(getResources().getColor(R.color.red));
        }else{
            buttons[id].setBackgroundColor(getResources().getColor(R.color.red));
        }

        if(id == 1 ||id == 2){
            ClimbLvl2 = "0";
            ClimbLvl3 = "0";
            DidNotTry = "0";
        }else if(id == 3 ||id == 4){
            ClimbLvl1 = "0";
            ClimbLvl3 = "0";
            DidNotTry = "0";
        }else if(id == 5 ||id == 6){
            ClimbLvl1 = "0";
            ClimbLvl2 = "0";
            DidNotTry = "0";
        }else{
            ClimbLvl1 = "0";
            ClimbLvl2 = "0";
            ClimbLvl3 = "0";
        }

    }
}
