package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

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
    int FailureHatchCount = 0;
    int Rocket3CargoCount = 0;
    int Rocket2CargoCount = 0;
    int Rocket1CargoCount = 0;
    int CargoShipCargoCount = 0;
    int FailureCargoCount = 0;



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
                //Maybe check for errors
                //make sure to pass on information
            }
        });
    }
}
