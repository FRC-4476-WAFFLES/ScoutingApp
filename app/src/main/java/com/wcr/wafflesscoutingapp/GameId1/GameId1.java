package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId1 extends AppCompatActivity {
    private static final String TAG = "GameId1.java";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id_1);
        final GlobalData app_data = (GlobalData)getApplicationContext();
        System.out.println("in "+TAG);
        if(app_data.getGame_state().equals(getString(R.string.pregame))){
            Log.d(TAG, "Entering Pregame");
            Intent startIntent = new Intent(getApplicationContext(), GameId1Before.class);
            startActivity(startIntent);
        }else if(app_data.getGame_state().equals(getString(R.string.sandstorm))){
            Log.d(TAG, "Entering Sandstorm");
            Intent startIntent = new Intent(getApplicationContext(), GameId1Autonomous.class);
            startActivity(startIntent);
        }else if(app_data.getGame_state().equals(getString(R.string.teleop))){
            Log.d(TAG, "Entering Teleop");
            Intent startIntent = new Intent(getApplicationContext(), GameId1Teleop.class);
            startActivity(startIntent);
        }else if(app_data.getGame_state().equals(getString(R.string.endgame))){
            Log.d(TAG, "Entering Endgame");
            Intent startIntent = new Intent(getApplicationContext(), GameId1Endgame.class);
            startActivity(startIntent);
        }else if(app_data.getGame_state().equals(getString(R.string.transmit))){
            Log.d(TAG, "Entering Transmit");
            Intent startIntent = new Intent(getApplicationContext(), GameId1Transmit.class);
            startActivity(startIntent);
        }else{
            Log.d(TAG, "hit else in oncreate method, gamestate = "+ app_data.getGame_state());
        }
    }
}
