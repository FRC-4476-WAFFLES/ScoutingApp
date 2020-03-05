package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class GameId1Endgame extends AppCompatActivity {
    private static final String TAG = "GameId1Endgame";
    Typeface CooperBlack;
    GlobalData app_data;
    double LinupTime = 0;
    final List<String> genericButtonStates = Arrays.asList("1", "0", "-1");

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
            final List<String> climbStates = Arrays.asList("Success", "N/A", "Fail");
            String savedClimbState = app_data.getMatchDataId(18);
            if(savedClimbState == null){savedClimbState = "";}
            //Load from saved data
            for(String state : climbStates){
                if(state.equals(savedClimbState)){
                    UpdateTriColour(ClimbButtons, climbStates.indexOf(state));
                }
            }

            yesClimb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ClimbButtons, 0);
                    app_data.setMatchDataId(18, climbStates.get(0), GameId1Endgame.this);
                }
            });

            noClimb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ClimbButtons, 1);
                    app_data.setMatchDataId(18, climbStates.get(1), GameId1Endgame.this);
                }
            });

            failClimb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ClimbButtons, 2);
                    app_data.setMatchDataId(18, climbStates.get(2), GameId1Endgame.this);
                }
            });

        }

        //Lineup Time text box
        {
            final EditText linupTime = (EditText)findViewById(R.id.climbTimeEditText);
            linupTime.setText(app_data.getMatchDataId(19));
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
                    Log.d(TAG, "length of lineup time: " + in.length() + " " + in.getClass().getName());
                    if(!in.equals("") && in.length() < 3){
                        LinupTime = Double.parseDouble(in);
                        //save lineup time text
                        app_data.setMatchDataId(19, ""+LinupTime, GameId1Endgame.this);
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

            String savedState = app_data.getMatchDataId(20);
            if(savedState == null){savedState = "";}
            //Load from saved data
            for(String state : genericButtonStates){
                if(state.equals(savedState)){
                    UpdateTriColour(CarrierButtons, genericButtonStates.indexOf(state));
                }
            }

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

            String savedState = app_data.getMatchDataId(21);
            if(savedState == null){savedState = "";}
            //Load from saved data
            for(String state : genericButtonStates){
                if(state.equals(savedState)){
                    UpdateTriColour(CargoButtons, genericButtonStates.indexOf(state));
                }
            }

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
            final List<Button> PlaceButtons = Arrays.asList(sidePlace, centerPlace, nonePlace);
            final List<String> placeStates = Arrays.asList("Side", "Middle", "None");

            String savedState = app_data.getMatchDataId(22);
            if(savedState == null){savedState = "";}
            //Load from saved data
            for(String state : placeStates){
                if(state.equals(savedState)){
                    UpdateTriColour(PlaceButtons, placeStates.indexOf(state));
                }
            }

            sidePlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(PlaceButtons, 0);
                    app_data.setMatchDataId(22, "Side", GameId1Endgame.this);
                }
            });
            centerPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(PlaceButtons, 1);
                    app_data.setMatchDataId(22, "Middle", GameId1Endgame.this);
                }
            });
            nonePlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(PlaceButtons, 2);
                    app_data.setMatchDataId(22, "None", GameId1Endgame.this);
                }
            });
        }

        //Parked Select select
        {
            final Button yesParked = (Button)findViewById(R.id.parkedYesButton);
            final Button noParked = (Button)findViewById(R.id.parkedNoButton);
            final Button failedParked = (Button)findViewById(R.id.parkedFailButton);
            final List<Button> ParkedButtons = Arrays.asList(yesParked, noParked, failedParked);
            final List<String> parkedStates = Arrays.asList("Success", "N/A", "Fail");

            String savedState = app_data.getMatchDataId(23);
            if(savedState == null){savedState = "";}
            //Load from saved data
            for(String state : parkedStates){
                if(state.equals(savedState)){
                    UpdateTriColour(ParkedButtons, parkedStates.indexOf(state));
                }
            }

            yesParked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ParkedButtons, 0);
                    app_data.setMatchDataId(23, "Success", GameId1Endgame.this);
                }
            });
            noParked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ParkedButtons, 1);
                    app_data.setMatchDataId(23, "N/A", GameId1Endgame.this);
                }
            });
            failedParked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UpdateTriColour(ParkedButtons, 2);
                    app_data.setMatchDataId(23, "Fail", GameId1Endgame.this);
                }
            });
        }

        //Continue Button
        final Button continueButton = (Button)findViewById(R.id.endgameContinueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmpClimb = app_data.getMatchDataId(18);
                String tmpLineupTime = app_data.getMatchDataId(19);
                String tmpCarrier = app_data.getMatchDataId(20);
                String tmpCargo = app_data.getMatchDataId(21);
                String tmpBarSpot = app_data.getMatchDataId(22);
                String tmpParked = app_data.getMatchDataId(23);

                if(tmpLineupTime == null) {tmpLineupTime = "";}
                if(tmpCarrier == null) {tmpCarrier = "";}
                if(tmpCargo == null) {tmpCargo = "";}
                if(tmpBarSpot == null) {tmpBarSpot = "";}
                if(tmpParked == null) {tmpParked = "";}


                if(tmpCarrier.equals("") || tmpCargo.equals("") || tmpBarSpot.equals("") || tmpParked.equals("")){
                    Toast.makeText(GameId1Endgame.this, "please make sure all fields are filled...", Toast.LENGTH_SHORT).show();
                }else if(tmpClimb.equals("Success")) {
                    if (tmpParked.equals("Success") || tmpParked.equals("Fail")) {
                        Toast.makeText(GameId1Endgame.this, "Cant park and hang at the same time.", Toast.LENGTH_SHORT).show();
                    }
                    if(LinupTime >=0){
                        Toast.makeText(GameId1Endgame.this, "It will take more than 0s to line up", Toast.LENGTH_SHORT).show();
                    }
                }else if(tmpCarrier.equals("1")) {
                    if (tmpCargo.equals("1") || tmpCargo.equals("-1")) {
                        Toast.makeText(GameId1Endgame.this, "cant be carrier and cargo", Toast.LENGTH_SHORT).show();
                    }
                    if (tmpClimb.equals("N/A") || tmpClimb.equals("Fail")) {
                        Toast.makeText(GameId1Endgame.this, "Must climb to be a Carrier", Toast.LENGTH_SHORT).show();
                    }
                    if (tmpBarSpot.equals("None")) {
                        Toast.makeText(GameId1Endgame.this, "Must have bar spot to be Carrier", Toast.LENGTH_SHORT).show();
                    }
                    if (tmpParked.equals("Success") || tmpParked.equals("Fail")) {
                        Toast.makeText(GameId1Endgame.this, "Cant park and hang at the same time.", Toast.LENGTH_SHORT).show();
                    }
                    if (LinupTime >= 0) {
                        Toast.makeText(GameId1Endgame.this, "It will Definitely take more than 0s to line up", Toast.LENGTH_SHORT).show();
                    }
                }else if(tmpCargo.equals("1")){
                    if(LinupTime >=0){
                        Toast.makeText(GameId1Endgame.this, "It will Definitely take more than 0s to line up", Toast.LENGTH_SHORT).show();
                    }
                    if(tmpParked.equals("Success") || tmpParked.equals("Fail")){
                        Toast.makeText(GameId1Endgame.this, "Cant park and hang at the same time.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    //Start Next Page
                    app_data.setGame_state(getString(R.string.postgame));
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }
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

//    private int GetIntFromArray(String s){
//        int parsed;
//        try {
//            parsed = Integer.parseInt(s);
//        } catch (Exception e){
//            Log.e(TAG, "string value '"  + s + "' could not be converted to an integer");
//            parsed = 0;
//        }
//        return parsed;
//    }
}
