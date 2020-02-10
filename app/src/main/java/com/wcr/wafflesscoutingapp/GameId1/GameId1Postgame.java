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
import com.wcr.wafflesscoutingapp.R;

public class GameId1Postgame extends AppCompatActivity {
    Typeface CooperBlack;
    String FinalScore = "0";
    String AllianceFouls = "0";
    String ScoutComments = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_postgame);

        //global data import
        final GlobalData app_data = (GlobalData)getApplicationContext();
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");

        //title
        TextView titleTextView = (TextView)findViewById(R.id.postGameTitleTextView);
        titleTextView.setTypeface(CooperBlack);

        //edit texts
        final EditText finalScoreEditText = (EditText)findViewById(R.id.allianceScoreEditText);
        final EditText allianceFoulsEditText = (EditText)findViewById(R.id.allianceFoulsEditText);
        final EditText commentsEditText = (EditText)findViewById(R.id.commentsEditText);

        final CheckBox crossedInitiationLine = (CheckBox) findViewById(R.id.levelClimbButton);
        crossedInitiationLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crossedInitiationLine.isChecked()) {
                    app_data.setMatchDataId(25, "1", GameId1Postgame.this);
                }else{
                    app_data.setMatchDataId(25, "0", GameId1Postgame.this);
                }
            }
        });


        //continue button
        final Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FinalScore = finalScoreEditText.getText().toString();
                app_data.setMatchDataId(24, FinalScore, GameId1Postgame.this);
                AllianceFouls = allianceFoulsEditText.getText().toString();
                app_data.setMatchDataId(26, AllianceFouls, GameId1Postgame.this);
                ScoutComments = commentsEditText.getText().toString();
                app_data.setMatchDataId(27, ScoutComments, GameId1Postgame.this);

                //make sure to check all fields are filled
                if(!FinalScore.equals("") && !AllianceFouls.equals("") && !ScoutComments.equals("")) {
                    app_data.setGame_state(getString(R.string.transmit));
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else{
                    Toast.makeText(GameId1Postgame.this, "please make sure all fields are filled...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
