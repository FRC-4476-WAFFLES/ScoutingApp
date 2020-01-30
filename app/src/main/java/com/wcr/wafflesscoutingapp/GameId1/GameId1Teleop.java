package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId1Teleop extends AppCompatActivity {
    Typeface CooperBlack;
    boolean Erase = false;
    int InnerAutoScore = 0;
    int OuterAutoScore = 0;
    int LowerAutoScore = 0;
    int FailedAutoScore = 0;
    //TODO: make buttons save data on press



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_teleop);

        //global data import
        final GlobalData app_data = (GlobalData)getApplicationContext();

        //title
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        //Score inner position
        {
            final Button scoreInner = (Button) findViewById(R.id.innerScoreTeleopButton);
            scoreInner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Erase) {
                        InnerAutoScore = InnerAutoScore - 1;
                        scoreInner.setText("Inner: " + InnerAutoScore);
                    } else {
                        InnerAutoScore = InnerAutoScore + 1;
                        scoreInner.setText("Inner: " + InnerAutoScore);
                    }
                    app_data.setMatchDataId(12, "" + InnerAutoScore, GameId1Teleop.this);
                }
            });
        }

        //Score outer position
        {
            final Button scoreOuter = (Button) findViewById(R.id.outerScoreTeleopButton);
            scoreOuter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Erase) {
                        OuterAutoScore = OuterAutoScore - 1;
                        scoreOuter.setText("Outer: " + OuterAutoScore);
                    } else {
                        OuterAutoScore = OuterAutoScore + 1;
                        scoreOuter.setText("Outer: " + OuterAutoScore);
                    }
                    app_data.setMatchDataId(13, "" + OuterAutoScore, GameId1Teleop.this);
                }
            });
        }

        //Score Lower position
        {
            final Button scoreLower = (Button) findViewById(R.id.lowerScoreTeleopButton);
            scoreLower.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Erase) {
                        LowerAutoScore = LowerAutoScore - 1;
                        scoreLower.setText("Lower: " + LowerAutoScore);
                    } else {
                        LowerAutoScore = LowerAutoScore + 1;
                        scoreLower.setText("Lower: " + LowerAutoScore);
                    }
                    app_data.setMatchDataId(14, "" + LowerAutoScore, GameId1Teleop.this);
                }
            });
        }

        //failed shots
        {
            final Button scoreFailed = (Button) findViewById(R.id.failedScoreTeleopButton);
            scoreFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Erase) {
                        FailedAutoScore = FailedAutoScore - 1;
                        scoreFailed.setText("Failed: " + FailedAutoScore);
                    } else {
                        FailedAutoScore = FailedAutoScore + 1;
                        scoreFailed.setText("Failed: " + FailedAutoScore);
                    }
                    app_data.setMatchDataId(15, "" + FailedAutoScore, GameId1Teleop.this);
                }
            });
        }

        //Erase Button
        final Button eraseButton = (Button)findViewById(R.id.eraseButton);
        eraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Erase){
                    Erase = false;
                    eraseButton.setBackgroundColor(getResources().getColor(R.color.grey));
                }else{
                    Erase = true;
                    eraseButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
            }
        });


        //Continue Button
        final Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                app_data.setGame_state(getString(R.string.endgame));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });
    }
}
