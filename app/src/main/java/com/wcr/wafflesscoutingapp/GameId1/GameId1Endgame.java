package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class GameId1Endgame extends AppCompatActivity {
    Typeface CooperBlack;
    GlobalData app_data;
    int LinupTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_endgame);

        //import all the values external to the class
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        app_data = (GlobalData)getApplicationContext();

        //update the font on the title.
        TextView title = (TextView) findViewById(R.id.endgameTitleTextView);
        title.setTypeface(CooperBlack);

        //Climb stuff
        {
            //setup buttons
            Button yesClimb = (Button)findViewById(R.id.climbYesButton);
            Button noClimb = (Button)findViewById(R.id.climbNoButton);
            Button failClimb = (Button)findViewById(R.id.climbFailButton);
            final List<Button> ClimbButtons = Arrays.asList(yesClimb, noClimb, failClimb);

            yesClimb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ClimbButtons, 0);
                    app_data.setMatchDataId(18, "Success", GameId1Endgame.this);
                }
            });

            noClimb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ClimbButtons, 1);
                    app_data.setMatchDataId(18, "N/A", GameId1Endgame.this);
                }
            });

            failClimb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ClimbButtons, 2);
                    app_data.setMatchDataId(18, "Fail", GameId1Endgame.this);
                }
            });

        }

        //Lineup Time text box
        {
            final EditText linupTime = (EditText)findViewById(R.id.climbTimeEditText);
            linupTime.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    String in = linupTime.getText().toString();
                    if(!in.equals("")){
                        LinupTime = Integer.parseInt(in);
                    }
                }
            });
        }

        //Carrier select
        {
            final Button yesCarrier = (Button)findViewById(R.id.carrierYesButton);
            final Button noCarrier = (Button)findViewById(R.id.carrierNoButton);
            final Button failedCarrier = (Button)findViewById(R.id.carrierFailButton);
            final List<Button> CarrierButtons = Arrays.asList(yesCarrier, noCarrier, failedCarrier);

            yesCarrier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CarrierButtons, 0);
                    app_data.setMatchDataId(20, "1", GameId1Endgame.this);
                }
            });
            noCarrier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CarrierButtons, 1);
                    app_data.setMatchDataId(20, "0", GameId1Endgame.this);
                }
            });
            failedCarrier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CarrierButtons, 2);
                    app_data.setMatchDataId(20, "-1", GameId1Endgame.this);
                }
            });
        }

        //Cargo select
        {
            final Button yesCargo = (Button)findViewById(R.id.cargoYesButton);
            final Button noCargo = (Button)findViewById(R.id.cargoNoButton);
            final Button failedCargo = (Button)findViewById(R.id.cargoFailButton);
            final List<Button> CargoButtons = Arrays.asList(yesCargo, noCargo, failedCargo);

            yesCargo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 0);
                    app_data.setMatchDataId(21, "1", GameId1Endgame.this);
                }
            });
            noCargo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 1);
                    app_data.setMatchDataId(21, "0", GameId1Endgame.this);
                }
            });
            failedCargo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 2);
                    app_data.setMatchDataId(21, "-1", GameId1Endgame.this);
                }
            });
        }

        //Place select
        {
            final Button sidePlace = (Button)findViewById(R.id.placeSideButton);
            final Button centerPlace = (Button)findViewById(R.id.placeCenterButton);
            final Button nonePlace = (Button)findViewById(R.id.placeNoneButton);
            final List<Button> CargoButtons = Arrays.asList(sidePlace, centerPlace, nonePlace);

            sidePlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 0);
                    app_data.setMatchDataId(22, "Side", GameId1Endgame.this);
                }
            });
            centerPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 1);
                    app_data.setMatchDataId(22, "Middle", GameId1Endgame.this);
                }
            });
            nonePlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 2);
                    app_data.setMatchDataId(22, "None", GameId1Endgame.this);
                }
            });
        }

        //Parked Select select
        {
            final Button yesParked = (Button)findViewById(R.id.parkedYesButton);
            final Button noParked = (Button)findViewById(R.id.parkedNoButton);
            final Button failedParked = (Button)findViewById(R.id.parkedFailButton);
            final List<Button> CargoButtons = Arrays.asList(yesParked, noParked, failedParked);

            yesParked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 0);
                    app_data.setMatchDataId(23, "Success", GameId1Endgame.this);
                }
            });
            noParked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 1);
                    app_data.setMatchDataId(23, "N/A", GameId1Endgame.this);
                }
            });
            failedParked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(CargoButtons, 2);
                    app_data.setMatchDataId(23, "Fail", GameId1Endgame.this);
                }
            });
        }

        //Continue Button
        final Button continueButton = (Button)findViewById(R.id.endgameContinueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //save lineup time text
                app_data.setMatchDataId(19, ""+LinupTime, GameId1Endgame.this);

                //Start Next Page
                app_data.setGame_state(getString(R.string.postgame));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });
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
