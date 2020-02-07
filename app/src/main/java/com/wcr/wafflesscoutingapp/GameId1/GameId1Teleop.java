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

import java.util.Arrays;
import java.util.List;

public class GameId1Teleop extends AppCompatActivity {
    Typeface CooperBlack;
    List<Button> playStyleButtons;
    boolean Erase = false;
    int InnerAutoScore = 0;
    int OuterAutoScore = 0;
    int LowerAutoScore = 0;
    int FailedAutoScore = 0;
    int RotationControl = 0;
    int PositionControl = 0;
    int PlayStyleButtonPressed = 0;
    //TODO: add the current team being scouted to the top corner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_teleop);

        //global data import
        final GlobalData app_data = (GlobalData)getApplicationContext();

        //title
        TextView titleTextView = (TextView)findViewById(R.id.teleopTitleTextView);
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
                    app_data.setMatchDataId(11, "" + InnerAutoScore, GameId1Teleop.this);
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
                    app_data.setMatchDataId(12, "" + OuterAutoScore, GameId1Teleop.this);
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
                    app_data.setMatchDataId(13, "" + LowerAutoScore, GameId1Teleop.this);
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
                    app_data.setMatchDataId(14, "" + FailedAutoScore, GameId1Teleop.this);
                }
            });
        }

        //Rotation Control stuff
        {
            final Button rotationControlFailed = (Button) findViewById(R.id.rotationControlFailedButton);
            final Button rotationControlYes = (Button) findViewById(R.id.rotationControlYesButton);
            final Button rotationControlNo = (Button) findViewById(R.id.rotationControlNoButton);

            rotationControlFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RotationControl = -1;
                    UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo, app_data);
                }
            });

            rotationControlYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RotationControl = 1;
                    UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo, app_data);
                }
            });
            rotationControlNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RotationControl = 0;
                    UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo, app_data);
                }
            });
        }

        //Position Control stuff
        {
            final Button positionControlFailed = (Button) findViewById(R.id.positionControlFailedButton);
            final Button positionControlYes = (Button) findViewById(R.id.positionControlYesButton);
            final Button positionControlNo = (Button) findViewById(R.id.positionControlNoButton);

            positionControlFailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PositionControl = -1;
                    UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo, app_data);
                }
            });

            positionControlYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PositionControl = 1;
                    UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo, app_data);
                }
            });
            positionControlNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PositionControl = 0;
                    UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo, app_data);
                }
            });
        }

        {
            final Button nAButton = findViewById(R.id.nAPlayButton);
            final Button farShotButton = findViewById(R.id.farShotPlayButton);
            final Button closeShotButton = findViewById(R.id.closeShotPlayButton);
            final Button defenceButton = findViewById(R.id.defencePlayButton);
            final Button overflowButton = findViewById(R.id.overflowPlayButton);
            final Button controlPanelButton = findViewById(R.id.controlPanelPlayButton);
            playStyleButtons = Arrays.asList(nAButton, farShotButton, closeShotButton, defenceButton, overflowButton, controlPanelButton);

            nAButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayStyleButtonPressed = 0;
                    UpdatePlayStyle(playStyleButtons, app_data);
                }
            });

            farShotButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayStyleButtonPressed = 1;
                    UpdatePlayStyle(playStyleButtons, app_data);
                }
            });

            closeShotButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayStyleButtonPressed = 2;
                    UpdatePlayStyle(playStyleButtons, app_data);
                }
            });

            defenceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayStyleButtonPressed = 3;
                    UpdatePlayStyle(playStyleButtons, app_data);
                }
            });

            overflowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayStyleButtonPressed = 4;
                    UpdatePlayStyle(playStyleButtons, app_data);
                }
            });

            controlPanelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlayStyleButtonPressed = 5;
                    UpdatePlayStyle(playStyleButtons, app_data);
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
    private void UpdateRotationControlColours(Button failed, Button yes, Button no, GlobalData app_data){
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
        app_data.setMatchDataId(15, "" + RotationControl, GameId1Teleop.this);
    }

    private void UpdatePositionControlColours(Button failed, Button yes, Button no, GlobalData app_data){
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
        app_data.setMatchDataId(16, "" + PositionControl, GameId1Teleop.this);
    }

    private void UpdatePlayStyle(List<Button> buttonList, GlobalData app_data){
        for (int i =0; i < 6; i++) {
            Button curentButton = buttonList.get(i);
            if(i == PlayStyleButtonPressed){
                curentButton.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                app_data.setMatchDataId(17, "" + curentButton.getText(), GameId1Teleop.this);
            }else{
                curentButton.setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
    }
}
