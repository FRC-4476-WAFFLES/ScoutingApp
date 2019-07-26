package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.MainActivity;
import com.wcr.wafflesscoutingapp.R;

public class GameId1Before extends AppCompatActivity {
    Typeface CooperBlack;
    String DriverStation = "";
    String StartOnLevel2 = "";
    String StartingPosition = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_before);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        final GlobalData app_data = (GlobalData)getApplicationContext();
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        //Team Number
        final EditText teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);

        //Match Number
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);

        //Driver Station buttons
        DriverStation = "";
        final Button blue1Button = (Button)findViewById(R.id.blue1Button);
        blue1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DSLocBtn(0);
                DriverStation = "Blue1";
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
        StartOnLevel2 = "False";
        final CheckBox lvl2 = (CheckBox) findViewById(R.id.startPosCheckBox);
        if(lvl2.isChecked()) {
            StartOnLevel2 = "True";
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
                StartingPosition = "left";
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                centerButton.setBackgroundColor(getResources().getColor(R.color.grey));
                leftButton.setBackgroundColor(getResources().getColor(R.color.grey));
                rightButton.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                StartingPosition = "right";
            }
        });
        centerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rightButton.setBackgroundColor(getResources().getColor(R.color.grey));
                leftButton.setBackgroundColor(getResources().getColor(R.color.grey));
                centerButton.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                StartingPosition = "center";
            }
        });



        //continue button
        final Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TeamNumber = teamNumberEditText.getText().toString();
                String MatchNumber = matchNumberEditText.getText().toString();
                //make sure to check all fields are filled
                if(DriverStation != "" && StartOnLevel2 != "" && StartingPosition != "" && TeamNumber != "" && MatchNumber != "") {
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
}

