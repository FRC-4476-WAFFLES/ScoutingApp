package com.wcr.wafflesscoutingapp.GameId1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wcr.wafflesscoutingapp.GlobalData;
import com.wcr.wafflesscoutingapp.R;

public class GameId1Autonomous extends AppCompatActivity {
    Typeface CooperBlack;
    String HasCrossedHabLine = "";
    String RocketShipLevel1Hatch = "";
    String RocketShipLevel2Hatch = "";
    String RocketShipLevel3Hatch = "";
    String RocketShipLevel1Cargo = "";
    String RocketShipLevel2Cargo = "";
    String RocketShipLevel3Cargo = "";
    String CargoShipFrontHatch = "";
    String CargoShipFrontCargo = "";
    String CargoShipSideHatch = "";
    String CargoShipSideCargo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_id1_autonomous);

        //global data import
        final GlobalData app_data = (GlobalData)getApplicationContext();

        //title
        TextView titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setTypeface(CooperBlack);

        // Hab line check box
        HasCrossedHabLine = "False";
        final CheckBox crossedHabLine = (CheckBox) findViewById(R.id.crossedHabLineCheckBox);
        if(crossedHabLine.isChecked()) {
            HasCrossedHabLine = "True";
        }

        //Rocket ship Hatches
        {
            final Button rocketHatch1 = (Button) findViewById(R.id.Rocket1HatchButton);
            rocketHatch1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel1Hatch == "") {
                        RocketShipLevel1Hatch = "o";
                        rocketHatch1.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel1Hatch == "o") {
                        RocketShipLevel1Hatch = "x";
                        rocketHatch1.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel1Hatch = "";
                        rocketHatch1.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                    }
                }
            });
        }//Rocket ship Level 1

        {
            final Button rocketHatch2 = (Button) findViewById(R.id.Rocket2HatchButton);
            rocketHatch2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel2Hatch == "") {
                        RocketShipLevel2Hatch = "o";
                        rocketHatch2.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel2Hatch == "o") {
                        RocketShipLevel2Hatch = "x";
                        rocketHatch2.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel2Hatch = "";
                        rocketHatch2.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                    }
                }
            });
        }//Rocket ship Level 2

        {
            final Button rocketHatch3 = (Button) findViewById(R.id.Rocket3HatchButton);
            rocketHatch3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel3Hatch == "") {
                        RocketShipLevel3Hatch = "o";
                        rocketHatch3.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel3Hatch == "o") {
                        RocketShipLevel3Hatch = "x";
                        rocketHatch3.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel3Hatch = "";
                        rocketHatch3.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                    }
                }
            });
        }//Rocket ship Level 3

        //Rocket ship Cargo
        {
            final Button rocketCargo1 = (Button) findViewById(R.id.Rocket1CargoButton);
            rocketCargo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel1Cargo == "") {
                        RocketShipLevel1Cargo = "o";
                        rocketCargo1.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel1Cargo == "o") {
                        RocketShipLevel1Cargo = "x";
                        rocketCargo1.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel1Cargo = "";
                        rocketCargo1.setBackgroundColor(getResources().getColor(R.color.ballOrange));
                    }
                }
            });
        }//Rocket ship Level 1

        {
            final Button rocketCargo2 = (Button) findViewById(R.id.Rocket2CargoButton);
            rocketCargo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel2Cargo == "") {
                        RocketShipLevel2Cargo = "o";
                        rocketCargo2.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel2Cargo == "o") {
                        RocketShipLevel2Cargo = "x";
                        rocketCargo2.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel2Cargo = "";
                        rocketCargo2.setBackgroundColor(getResources().getColor(R.color.ballOrange));
                    }
                }
            });
        }//Rocket ship Level 2

        {
            final Button rocketCargo3 = (Button) findViewById(R.id.Rocket3CargoButton);
            rocketCargo3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel3Cargo == "") {
                        RocketShipLevel3Cargo = "o";
                        rocketCargo3.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel3Cargo == "o") {
                        RocketShipLevel3Cargo = "x";
                        rocketCargo3.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel3Cargo = "";
                        rocketCargo3.setBackgroundColor(getResources().getColor(R.color.ballOrange));
                    }
                }
            });
        }//Rocket ship Level 3

        //Cargo Ship Front
        {
            final Button hatchFront = (Button) findViewById(R.id.hatchFrontButton);
            hatchFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CargoShipFrontHatch == "") {
                        CargoShipFrontHatch = "o";
                        hatchFront.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipFrontHatch == "o") {
                        CargoShipFrontHatch = "x";
                        hatchFront.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipFrontHatch = "";
                        hatchFront.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                    }
                }
            });
        }//front hatch

        {
            final Button cargoFront = (Button) findViewById(R.id.cargoFrontButton);
            cargoFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CargoShipFrontCargo == "") {
                        CargoShipFrontCargo = "o";
                        cargoFront.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipFrontCargo == "o") {
                        CargoShipFrontCargo = "x";
                        cargoFront.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipFrontCargo = "";
                        cargoFront.setBackgroundColor(getResources().getColor(R.color.ballOrange));
                    }
                }
            });
        }//front cargo

        //Cargo Ship Side
        {
            final Button hatchSide = (Button) findViewById(R.id.hatchSideButton);
            hatchSide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CargoShipSideHatch == "") {
                        CargoShipSideHatch = "o";
                        hatchSide.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipSideHatch == "o") {
                        CargoShipSideHatch = "x";
                        hatchSide.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipSideHatch = "";
                        hatchSide.setBackgroundColor(getResources().getColor(R.color.wafflesYellow));
                    }
                }
            });
        }//front hatch

        {
            final Button cargoSide = (Button) findViewById(R.id.cargoSideButton);
            cargoSide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (CargoShipSideCargo == "") {
                        CargoShipSideCargo = "o";
                        cargoSide.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipSideCargo == "o") {
                        CargoShipSideCargo = "x";
                        cargoSide.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipSideCargo = "";
                        cargoSide.setBackgroundColor(getResources().getColor(R.color.ballOrange));
                    }
                }
            });
        }//front cargo

        //Continue Button
        Button continueButton = (Button)findViewById(R.id.continueToTeleopButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make sure to pass on the data.
                app_data.setGame_state(getString(R.string.teleop));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });

    }
}
