package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class GameId1Teleop extends AppCompatActivity {
    private static final String TAG = "GameId1Teleop";
    Typeface CooperBlack;
    List<Button> playStyleButtons;
    boolean Erase = false;
    int InnerTeleopScore = 0;
    int OuterTeleopScore = 0;
    int LowerTeleopScore = 0;
    int FailedTeleopScore = 0;
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
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");

        //title
        TextView titleTextView = (TextView)findViewById(R.id.teleopTitleTextView);
        titleTextView.setTypeface(CooperBlack);

        //Score inner position
        final Button scoreInner = (Button) findViewById(R.id.innerScoreTeleopButton);
        //for when the screen rotates, or if the activity is restarted from a previous state.
        InnerTeleopScore = GetIntFromArray("" + app_data.getMatchDataId(11));
        scoreInner.setText("Inner: " + InnerTeleopScore);
        //Update on button press
        scoreInner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && InnerTeleopScore > 0) {
                    InnerTeleopScore = InnerTeleopScore - 1;
                    scoreInner.setText("Inner: " + InnerTeleopScore);
                } else if(!Erase){
                    InnerTeleopScore = InnerTeleopScore + 1;
                    scoreInner.setText("Inner: " + InnerTeleopScore);
                }
                app_data.setMatchDataId(11, "" + InnerTeleopScore, GameId1Teleop.this);
            }
        });

        //Score outer position
        final Button scoreOuter = (Button) findViewById(R.id.outerScoreTeleopButton);
        //for when the screen rotates, or if the activity is restarted from a previous state.
        OuterTeleopScore = GetIntFromArray("" + app_data.getMatchDataId(12));
        scoreOuter.setText("Outer: " + OuterTeleopScore);
        //Update on button press
        scoreOuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && OuterTeleopScore > 0) {
                    OuterTeleopScore = OuterTeleopScore - 1;
                    scoreOuter.setText("Outer: " + OuterTeleopScore);
                } else if(!Erase){
                    OuterTeleopScore = OuterTeleopScore + 1;
                    scoreOuter.setText("Outer: " + OuterTeleopScore);
                }
                app_data.setMatchDataId(12, "" + OuterTeleopScore, GameId1Teleop.this);
            }
        });

        //Score Lower position
        final Button scoreLower = (Button) findViewById(R.id.lowerScoreTeleopButton);
        //for when the screen rotates, or if the activity is restarted from a previous state.
        LowerTeleopScore = GetIntFromArray("" + app_data.getMatchDataId(13));
        scoreLower.setText("Lower: " + LowerTeleopScore);
        //Update on button press
        scoreLower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && LowerTeleopScore > 0) {
                    LowerTeleopScore = LowerTeleopScore - 1;
                    scoreLower.setText("Lower: " + LowerTeleopScore);
                } else if(!Erase){
                    LowerTeleopScore = LowerTeleopScore + 1;
                    scoreLower.setText("Lower: " + LowerTeleopScore);
                }
                app_data.setMatchDataId(13, "" + LowerTeleopScore, GameId1Teleop.this);
            }
        });

        //failed shots
        final Button scoreFailed = (Button) findViewById(R.id.failedScoreTeleopButton);
        //for when the screen rotates, or if the activity is restarted from a previous state.
        FailedTeleopScore = GetIntFromArray("" + app_data.getMatchDataId(14));
        scoreFailed.setText("Failed: " + FailedTeleopScore);
        //Update on button press
        scoreFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Erase && FailedTeleopScore > 0) {
                    FailedTeleopScore = FailedTeleopScore - 1;
                    scoreFailed.setText("Failed: " + FailedTeleopScore);
                } else if(!Erase){
                    FailedTeleopScore = FailedTeleopScore + 1;
                    scoreFailed.setText("Failed: " + FailedTeleopScore);
                }
                app_data.setMatchDataId(14, "" + FailedTeleopScore, GameId1Teleop.this);
            }
        });

        //Rotation Control stuff
        {
            final Button rotationControlFailed = (Button) findViewById(R.id.rotationControlFailedButton);
            final Button rotationControlYes = (Button) findViewById(R.id.rotationControlYesButton);
            final Button rotationControlNo = (Button) findViewById(R.id.rotationControlNoButton);
            //Load Last
            RotationControl = GetIntFromArray("" + app_data.getMatchDataId(15));
            UpdateRotationControlColours(rotationControlFailed, rotationControlYes, rotationControlNo, app_data);

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
            //Load Last
            PositionControl = GetIntFromArray("" + app_data.getMatchDataId(16));
            UpdatePositionControlColours(positionControlFailed, positionControlYes, positionControlNo, app_data);

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
            List<String> playstyleButtonNames = Arrays.asList("Not Applicable", "Far Shot", "Close Shot", "Defence", "Overflow Cycle", "Control Panel");
            //Load Last
            String tmp = "" + app_data.getMatchDataId(17);
            try {
                for(String name : playstyleButtonNames) {
                    if(name.equals(tmp)){
                        PlayStyleButtonPressed = playstyleButtonNames.indexOf(name);
                        UpdatePlayStyle(playStyleButtons, app_data);
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "String Values could not be parsed as part of the list. skipping update of play style");
            }
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
        final List<Button> buttonsForErase = Arrays.asList(scoreInner, scoreOuter, scoreLower, scoreFailed, eraseButton);
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

    private void UpdateButtonColours(List<Button> list, int Colour){
        for(int i = 0; i<list.size(); i++){
            Button tmp = list.get(i);
            tmp.setBackgroundColor(Colour);
        }
    }

    private int GetIntFromArray(String s){
        int parsed;
        try {
            parsed = Integer.parseInt(s);
        } catch (Exception e){
            Log.e(TAG, "string value '"  + s + "' could not be converted to an integer");
            parsed = 0;
        }
        return parsed;
    }
}
