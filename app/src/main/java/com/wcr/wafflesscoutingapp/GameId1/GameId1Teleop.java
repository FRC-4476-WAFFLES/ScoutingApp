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
    int RotationControl = 0;
    int PositionControl = 0;
    //TODO: add the current team being scouted to the top corner



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

        //Rotation Control stuff
        {
            final Button rotationControlFailed = (Button) findViewById(R.id.rotationControlFailedButton);
            final Button rotationControlYes = (Button) findViewById(R.id.rotationControlFailedButton);
            final Button rotationControlNo = (Button) findViewById(R.id.rotationControlFailedButton);

            rotationControlFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RotationControl = -1;
                    UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo);
                }
            });

            rotationControlYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RotationControl = 1;
                    UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo);
                }
            });
            rotationControlNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RotationControl = 0;
                    UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo);
                }
            });
    }

        //Rotation Control stuff
        {
            final Button positionControlFailed = (Button) findViewById(R.id.positionControlFailedButton);
            final Button positionControlYes = (Button) findViewById(R.id.positionControlFailedButton);
            final Button positionControlNo = (Button) findViewById(R.id.positionControlFailedButton);

            positionControlFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PositionControl = -1;
                    UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo);
                }
            });

            positionControlYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PositionControl = 1;
                    UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo);
                }
            });
            positionControlNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PositionControl = 0;
                    UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo);
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
    private void UpdateRotationControlColours(Button failed, Button yes, Button no){
        if(RotationControl == -1){
            failed.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            yes.setBackgroundColor(getResources().getColor(R.color.grey));
            no.setBackgroundColor(getResources().getColor(R.color.grey));
        }else if(RotationControl == 0){
            no.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            yes.setBackgroundColor(getResources().getColor(R.color.grey));
            failed.setBackgroundColor(getResources().getColor(R.color.grey));
        }else if(RotationControl == 1){
            yes.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            no.setBackgroundColor(getResources().getColor(R.color.grey));
            failed.setBackgroundColor(getResources().getColor(R.color.grey));
        }else{
            yes.setBackgroundColor(getResources().getColor(R.color.grey));
            no.setBackgroundColor(getResources().getColor(R.color.grey));
            failed.setBackgroundColor(getResources().getColor(R.color.grey));
        }
    }

    private void UpdatePositionControlColours(Button failed, Button yes, Button no){
        if(PositionControl == -1){
            failed.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            yes.setBackgroundColor(getResources().getColor(R.color.grey));
            no.setBackgroundColor(getResources().getColor(R.color.grey));
        }else if(PositionControl == 0){
            no.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            yes.setBackgroundColor(getResources().getColor(R.color.grey));
            failed.setBackgroundColor(getResources().getColor(R.color.grey));
        }else if(PositionControl == 1){
            yes.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
            no.setBackgroundColor(getResources().getColor(R.color.grey));
            failed.setBackgroundColor(getResources().getColor(R.color.grey));
        }else{
            yes.setBackgroundColor(getResources().getColor(R.color.grey));
            no.setBackgroundColor(getResources().getColor(R.color.grey));
            failed.setBackgroundColor(getResources().getColor(R.color.grey));
        }
    }
}
