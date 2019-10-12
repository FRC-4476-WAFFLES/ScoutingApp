package com.wcr.wafflesscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.wcr.wafflesscoutingapp.GameId1.GameId1;
import com.wcr.wafflesscoutingapp.GameId3.GameId3;

public class ScoutData extends AppCompatActivity {
    Typeface CooperBlack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_data);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        final GlobalData app_data = (GlobalData)getApplicationContext();
        final EditText ScoutFirstName = (EditText)findViewById(R.id.scoutFirstNameEditText);
        final EditText ScoutLastName = (EditText)findViewById(R.id.scoutLastNameEditText);
        final Activity scout_data = this;
        TextView ScoutDataTitle = (TextView)findViewById(R.id.scoutDataTitleTextView);
        ScoutDataTitle.setTypeface(CooperBlack);
        if(app_data.getApp_config(0) != ""){//firstname
            ScoutFirstName.setText(app_data.getApp_config(0));
        }
        if(app_data.getApp_config(1) != ""){//lastname
            ScoutLastName.setText(app_data.getApp_config(1));
        }

        //event
        final Spinner spinner = (Spinner)findViewById(R.id.eventSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
        spinner.setSelection(myAdap.getPosition(app_data.getApp_config(2)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                app_data.setApp_config(scout_data, 2, String.valueOf(spinner.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button continueBtn = (Button)findViewById(R.id.continueButton);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = app_data.get_Game_index();
                //TODO: also save to match data array
                app_data.setGame_state(getString(R.string.pregame));
                app_data.setApp_config(scout_data, 0, ScoutFirstName.getText().toString());
                app_data.setApp_config(scout_data, 1, ScoutLastName.getText().toString());
                app_data.setApp_config(scout_data, 2, String.valueOf(spinner.getSelectedItem()));
                if(index == 0){
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else if(index == 3){
                    Intent startIntent = new Intent(getApplicationContext(), GameId3.class);
                    startActivity(startIntent);
                }
            }
        });

    }
}
