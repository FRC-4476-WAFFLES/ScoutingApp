package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import java.util.Arrays;
import java.util.List;

public class GameId1Autonomous extends AppCompatActivity {
    Typeface CooperBlack;
    boolean Erase = false;
    String HasCrossedInitiationLine = "";
    int InnerAutoScore = 0;
    int OuterAutoScore = 0;
    int LowerAutoScore = 0;
    int FailedAutoScore = 0;
    int BallsCollected = 0;
    //TODO: add the current team being scouted to the top corner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_autonomous);

        //globalg data import
        final GlobalData app_data = (GlobalData)getApplicationContext();
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");

        //title
        TextView titleTextView = (TextView)findViewById(R.id.autonomousTitleTextView);
        titleTextView.setTypeface(CooperBlack);

        // Hab line check box
        HasCrossedInitiationLine = "0";
        final CheckBox crossedInitiationLine = (CheckBox) findViewById(R.id.crossedInitLineCheckBox);
        crossedInitiationLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crossedInitiationLine.isChecked()) {
                    HasCrossedInitiationLine = "1";
                }else if(!Erase){
                    HasCrossedInitiationLine = "0";
                }
            }
        });


        //Score inner position
        final Button scoreInner = (Button) findViewById(R.id.innerScoreAutoButton);
        scoreInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && InnerAutoScore > 0) {
                    InnerAutoScore = InnerAutoScore - 1;
                    scoreInner.setText("Inner: " + InnerAutoScore);
                } else if(!Erase){
                    InnerAutoScore = InnerAutoScore + 1;
                    scoreInner.setText("Inner: " + InnerAutoScore);
                }
                app_data.setMatchDataId(6, "" + InnerAutoScore, GameId1Autonomous.this);
            }
        });

        //Score outer position
        final Button scoreOuter = (Button) findViewById(R.id.outerScoreAutoButton);
        scoreOuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && OuterAutoScore > 0) {
                    OuterAutoScore = OuterAutoScore - 1;
                    scoreOuter.setText("Outer: " + OuterAutoScore);
                } else if(!Erase){
                    OuterAutoScore = OuterAutoScore + 1;
                    scoreOuter.setText("Outer: " + OuterAutoScore);
                }
                app_data.setMatchDataId(7, "" + OuterAutoScore, GameId1Autonomous.this);
            }
        });

        //Score Lower position
        final Button scoreLower = (Button) findViewById(R.id.lowerScoreAutoButton);
        scoreLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && LowerAutoScore > 0) {
                    LowerAutoScore = LowerAutoScore - 1;
                    scoreLower.setText("Lower: " + LowerAutoScore);
                } else if(!Erase){
                    LowerAutoScore = LowerAutoScore + 1;
                    scoreLower.setText("Lower: " + LowerAutoScore);
                }
                app_data.setMatchDataId(8, "" + LowerAutoScore, GameId1Autonomous.this);
            }
        });

        //failed shots
        final Button scoreFailed = (Button) findViewById(R.id.failedScoreAutoButton);
        scoreFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && FailedAutoScore > 0) {
                    FailedAutoScore = FailedAutoScore - 1;
                    scoreFailed.setText("Failed: " + FailedAutoScore);
                } else if(!Erase){
                    FailedAutoScore = FailedAutoScore + 1;
                    scoreFailed.setText("Failed: " + FailedAutoScore);
                }
                app_data.setMatchDataId(9, "" + FailedAutoScore, GameId1Autonomous.this);
            }
        });

        //number of balls picked up
        final Button ballsPickedUp = (Button) findViewById(R.id.pickupButton);
        ballsPickedUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && BallsCollected > 0) {
                    BallsCollected = BallsCollected - 1;
                    ballsPickedUp.setText("Number Collected: " + BallsCollected);
                } else if(!Erase){
                    BallsCollected = BallsCollected + 1;
                    ballsPickedUp.setText("Number Collected: " + BallsCollected);
                }
                app_data.setMatchDataId(10, "" + BallsCollected, GameId1Autonomous.this);
            }
        });

        //Continue Button
        Button continueButton = (Button)findViewById(R.id.continueToTeleopButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make sure to pass on the data.
                app_data.setMatchDataId(5, HasCrossedInitiationLine, GameId1Autonomous.this);
                app_data.setGame_state(getString(R.string.teleop));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });

        //Erase Button
        final Button eraseButton = (Button)findViewById(R.id.AutoEraseButton);
        final List<Button> buttonsForErase = Arrays.asList(scoreInner, scoreOuter, scoreLower, scoreFailed, ballsPickedUp, eraseButton);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Erase){
                    Erase = false;
                    UpdateButtonColours(buttonsForErase, getResources().getColor(R.color.green));
                    eraseButton.setBackgroundColor(getResources().getColor(R.color.grey));
                }else{
                    Erase = true;
                    UpdateButtonColours(buttonsForErase, getResources().getColor(R.color.red));
                }
            }
        });
    }

    private void UpdateButtonColours(List<Button> list, int Colour){
        for(int i = 0; i<list.size(); i++){
            Button tmp = list.get(i);
            tmp.setBackgroundColor(Colour);
        }
    }
}
