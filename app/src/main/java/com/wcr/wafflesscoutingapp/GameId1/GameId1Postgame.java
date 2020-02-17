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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId1Postgame extends AppCompatActivity {
    private static final String TAG = "GameId1Postgame";
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
        // Load from last time clicked
        finalScoreEditText.setText(app_data.getMatchDataId(24));
        // Listen for clicks on this.
        finalScoreEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                app_data.setMatchDataId(24, finalScoreEditText.getText().toString(), GameId1Postgame.this);
                Log.d(TAG, "Focus on Final Score Changed");
            }
        });


        final EditText allianceFoulsEditText = (EditText)findViewById(R.id.allianceFoulsEditText);
        // Load from last time clicked
        allianceFoulsEditText.setText(app_data.getMatchDataId(26));
        // Listen for clicks on this.
        allianceFoulsEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                app_data.setMatchDataId(26, allianceFoulsEditText.getText().toString(), GameId1Postgame.this);
                Log.d(TAG, "Focus on Alliance Foul Score Changed");
            }
        });


        final EditText commentsEditText = (EditText)findViewById(R.id.commentsEditText);
        // Load everything that was typed before redraw
        commentsEditText.setText(app_data.getMatchDataId(27));
        // Listen for changes in the text
        commentsEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                app_data.setMatchDataId(27, commentsEditText.getText().toString(), GameId1Postgame.this);
            }
        });


        final CheckBox crossedInitiationLine = (CheckBox) findViewById(R.id.levelClimbButton);
        if(GetIntFromArray("" + app_data.getMatchDataId(5)) == 1){
            crossedInitiationLine.setChecked(true);
        }else{
            crossedInitiationLine.setChecked(false);
        }
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
