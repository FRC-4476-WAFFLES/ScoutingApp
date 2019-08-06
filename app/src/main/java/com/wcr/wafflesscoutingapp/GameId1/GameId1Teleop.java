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

public class GameId1Teleop extends AppCompatActivity {
    Typeface CooperBlack;
    boolean Erase = false;
    int Rocket3HatchCount = 0;
    int Rocket2HatchCount = 0;
    int Rocket1HatchCount = 0;
    int CargoShipHatchCount = 0;
    int FailuresHatchCount = 0;
    int Rocket3CargoCount = 0;
    int Rocket2CargoCount = 0;
    int Rocket1CargoCount = 0;
    int CargoShipCargoCount = 0;
    int FailuresCargoCount = 0;
    //TODO: make buttons save data on press



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_teleop);

        //global data import
        final GlobalData app_data = (GlobalData)getApplicationContext();

        //title
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        //Rocket 3
        {
            final Button Rocket3HatchButton = (Button)findViewById(R.id.Rocket3HatchButton);
            Rocket3HatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        Rocket3HatchCount = Rocket3HatchCount - 1;
                        Rocket3HatchButton.setText("HATCH: " + Rocket3HatchCount);
                    }else{
                        Rocket3HatchCount = Rocket3HatchCount + 1;
                        Rocket3HatchButton.setText("HATCH: " + Rocket3HatchCount);
                    }
                }
            });
        }

        {
            final Button Rocket3CargoButton = (Button)findViewById(R.id.Rocket3CargoButton);
            Rocket3CargoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        Rocket3CargoCount = Rocket3CargoCount - 1;
                        Rocket3CargoButton.setText("CARGO: " + Rocket3CargoCount);
                    }else{
                        Rocket3CargoCount = Rocket3CargoCount + 1;
                        Rocket3CargoButton.setText("CARGO: " + Rocket3CargoCount);
                    }
                }
            });
        }

        //Rocket 2
        {
            final Button Rocket2HatchButton = (Button)findViewById(R.id.Rocket2HatchButton);
            Rocket2HatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        Rocket2HatchCount = Rocket2HatchCount - 1;
                        Rocket2HatchButton.setText("HATCH: " + Rocket2HatchCount);
                    }else{
                        Rocket2HatchCount = Rocket2HatchCount + 1;
                        Rocket2HatchButton.setText("HATCH: " + Rocket2HatchCount);
                    }
                }
            });
        }

        {
            final Button Rocket2CargoButton = (Button)findViewById(R.id.Rocket2CargoButton);
            Rocket2CargoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        Rocket2CargoCount = Rocket2CargoCount - 1;
                        Rocket2CargoButton.setText("CARGO: " + Rocket2CargoCount);
                    }else{
                        Rocket2CargoCount = Rocket2CargoCount + 1;
                        Rocket2CargoButton.setText("CARGO: " + Rocket2CargoCount);
                    }
                }
            });
        }

        //Rocket 1
        {
            final Button Rocket1HatchButton = (Button)findViewById(R.id.Rocket1HatchButton);
            Rocket1HatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        Rocket1HatchCount = Rocket1HatchCount - 1;
                        Rocket1HatchButton.setText("HATCH: " + Rocket1HatchCount);
                    }else{
                        Rocket1HatchCount = Rocket1HatchCount + 1;
                        Rocket1HatchButton.setText("HATCH: " + Rocket1HatchCount);
                    }
                }
            });
        }

        {
            final Button Rocket1CargoButton = (Button)findViewById(R.id.Rocket1CargoButton);
            Rocket1CargoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        Rocket1CargoCount = Rocket1CargoCount - 1;
                        Rocket1CargoButton.setText("CARGO: " + Rocket1CargoCount);
                    }else{
                        Rocket1CargoCount = Rocket1CargoCount + 1;
                        Rocket1CargoButton.setText("CARGO: " + Rocket1CargoCount);
                    }
                }
            });
        }

        //Cargo Ship
        {
            final Button CargoShipHatchButton = (Button)findViewById(R.id.CargoShipHatchButton);
            CargoShipHatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        CargoShipHatchCount = CargoShipHatchCount - 1;
                        CargoShipHatchButton.setText("HATCH: " + CargoShipHatchCount);
                    }else{
                        CargoShipHatchCount = CargoShipHatchCount + 1;
                        CargoShipHatchButton.setText("HATCH: " + CargoShipHatchCount);
                    }
                }
            });
        }

        {
            final Button CargoShipCargoButton = (Button)findViewById(R.id.CargoShipCargoButton);
            CargoShipCargoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        CargoShipCargoCount = CargoShipCargoCount - 1;
                        CargoShipCargoButton.setText("CARGO: " + CargoShipCargoCount);
                    }else{
                        CargoShipCargoCount = CargoShipCargoCount + 1;
                        CargoShipCargoButton.setText("CARGO: " + CargoShipCargoCount);
                    }
                }
            });
        }

        //Failures
        {
            final Button FailuresHatchButton = (Button)findViewById(R.id.FailuresHatchButton);
            FailuresHatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        FailuresHatchCount = FailuresHatchCount - 1;
                        FailuresHatchButton.setText("HATCH: " + FailuresHatchCount);
                    }else{
                        FailuresHatchCount = FailuresHatchCount + 1;
                        FailuresHatchButton.setText("HATCH: " + FailuresHatchCount);
                    }
                }
            });
        }

        {
            final Button FailuresCargoButton = (Button)findViewById(R.id.FailuresCargoButton);
            FailuresCargoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Erase){
                        FailuresCargoCount = FailuresCargoCount - 1;
                        FailuresCargoButton.setText("CARGO: " + FailuresCargoCount);
                    }else{
                        FailuresCargoCount = FailuresCargoCount + 1;
                        FailuresCargoButton.setText("CARGO: " + FailuresCargoCount);
                    }
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
                //TODO: make sure to save the data
                //Maybe check for errors
                //make sure to pass on information
                app_data.setMatchDataId(24, Rocket1HatchCount + "");
                app_data.setMatchDataId(25, Rocket2HatchCount + "");
                app_data.setMatchDataId(26, Rocket3HatchCount + "");
                app_data.setMatchDataId(27, CargoShipHatchCount + "");
                app_data.setMatchDataId(28, FailuresHatchCount + "");

                app_data.setMatchDataId(29, Rocket1CargoCount + "");
                app_data.setMatchDataId(30, Rocket2CargoCount + "");
                app_data.setMatchDataId(31, Rocket3CargoCount + "");
                app_data.setMatchDataId(32, CargoShipCargoCount + "");
                app_data.setMatchDataId(33, FailuresCargoCount + "");

                app_data.setGame_state(getString(R.string.endgame));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });
    }
}
