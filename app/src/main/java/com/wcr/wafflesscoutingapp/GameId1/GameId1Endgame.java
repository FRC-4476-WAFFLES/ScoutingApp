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
import com.wcr.wafflesscoutingapp.ScoutData;

public class GameId1Endgame extends AppCompatActivity {
    Typeface CooperBlack;
    String ClimbLvl1 = "";
    String ClimbLvl2 = "";
    String ClimbLvl3 = "";
    String DidNotTry = "Yes";
    String AllianceScore = "";
    String AllianceFouls = "";
    String ScoutComments = "";
    boolean ClimbSet = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_endgame);

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
                    DidNotTry = "Yes";
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
                AllianceScore = allianceScoreEditText.getText().toString();
                AllianceFouls = allianceFoulsEditText.getText().toString();
                ScoutComments = commentsEditText.getText().toString();
                //make sure to check all fields are filled
                if(!AllianceScore.equals("") && !AllianceFouls.equals("") && !ScoutComments.equals("")) {
                    app_data.setGame_state(getString(R.string.transmit));
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else{
                    Toast.makeText(GameId1Endgame.this, "please make sure all fields are filled...", Toast.LENGTH_SHORT).show();
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
            ClimbLvl2 = "";
            ClimbLvl3 = "";
            DidNotTry = "";
        }else if(id == 3 ||id == 4){
            ClimbLvl1 = "";
            ClimbLvl3 = "";
            DidNotTry = "";
        }else if(id == 5 ||id == 6){
            ClimbLvl1 = "";
            ClimbLvl2 = "";
            DidNotTry = "";
        }else{
            ClimbLvl1 = "";
            ClimbLvl2 = "";
            ClimbLvl3 = "";
        }

    }
}
