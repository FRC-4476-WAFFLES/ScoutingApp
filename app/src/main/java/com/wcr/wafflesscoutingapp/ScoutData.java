package com.wcr.wafflesscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoutData extends AppCompatActivity {
    Typeface CooperBlack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout_data);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        final GlobalData app_data = (GlobalData)getApplicationContext();
        EditText ScoutFirstName = (EditText)findViewById(R.id.scoutFirstNameEditText);
        EditText ScoutLastName = (EditText)findViewById(R.id.scoutLastNameEditText);
        TextView ScoutDataTitle = (TextView)findViewById(R.id.scoutDataTitleTextView);
        ScoutDataTitle.setTypeface(CooperBlack);
        if(app_data.get_Scout_name_first() != ""){
            ScoutFirstName.setText(app_data.get_Scout_name_first());
        }
        if(app_data.get_Scout_name_last() != ""){
            ScoutLastName.setText(app_data.get_Scout_name_last());
        }
        Button continueBtn = (Button)findViewById(R.id.continueButton);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = app_data.get_Game_index();
                if(index == 0){
                    Intent startIntent = new Intent(getApplicationContext(), GameId0.class);
                    //show how to pass information to another activity
                    startActivity(startIntent);
                }else if(index == 1){
                    Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                    startActivity(startIntent);
                }else if(index == 2){
                    Intent startIntent = new Intent(getApplicationContext(), GameId2.class);
                    startActivity(startIntent);
                }else if(index == 3){
                    Intent startIntent = new Intent(getApplicationContext(), GameId3.class);
                    startActivity(startIntent);
                }
            }
        });
    }
}
