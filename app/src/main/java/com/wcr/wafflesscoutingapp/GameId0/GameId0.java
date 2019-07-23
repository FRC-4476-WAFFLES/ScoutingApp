package com.wcr.wafflesscoutingapp.GameId0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id_0);
        final GlobalData app_data = (GlobalData)getApplicationContext();
        if(app_data.getGame_state().equals(getString(R.string.pregame))){
            Intent startIntent = new Intent(getApplicationContext(), GameId0Before.class);
            startActivity(startIntent);
        }
    }
}
