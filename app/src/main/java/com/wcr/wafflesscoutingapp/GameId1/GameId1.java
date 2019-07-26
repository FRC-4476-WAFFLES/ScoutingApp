package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wcr.wafflesscoutingapp.GameId0.GameId0Before;
import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id_1);
        final GlobalData app_data = (GlobalData)getApplicationContext();
        if(app_data.getGame_state().equals(getString(R.string.pregame))){
            Intent startIntent = new Intent(getApplicationContext(), GameId1Before.class);
            startActivity(startIntent);
        }else if(app_data.getGame_state().equals(getString(R.string.sandstorm))){
            Intent startIntent = new Intent(getApplicationContext(), GameId1Autonomous.class);
            startActivity(startIntent);
        }
    }
}
