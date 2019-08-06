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
    //TODO: make buttons save data on press

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
        HasCrossedHabLine = "0";
        final CheckBox crossedHabLine = (CheckBox) findViewById(R.id.crossedHabLineCheckBox);
        if(crossedHabLine.isChecked()) {
            HasCrossedHabLine = "1";
        }

        //Rocket ship Hatches
        {
            final Button rocketHatch1 = (Button) findViewById(R.id.Rocket1HatchButton);
            rocketHatch1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (RocketShipLevel1Hatch == "") {
                        RocketShipLevel1Hatch = "0";
                        app_data.setMatchDataId(6, "1");
                        app_data.setMatchDataId(7, "0");
                        rocketHatch1.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel1Hatch == "0") {
                        RocketShipLevel1Hatch = "1";
                        app_data.setMatchDataId(6, "0");
                        app_data.setMatchDataId(7, "1");
                        rocketHatch1.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel1Hatch = "";
                        app_data.setMatchDataId(6, "0");
                        app_data.setMatchDataId(7, "0");

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
                        RocketShipLevel2Hatch = "0";
                        app_data.setMatchDataId(8, "1");
                        app_data.setMatchDataId(9, "0");
                        rocketHatch2.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel2Hatch == "0") {
                        RocketShipLevel2Hatch = "1";
                        app_data.setMatchDataId(8, "0");
                        app_data.setMatchDataId(9, "1");
                        rocketHatch2.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel2Hatch = "";
                        app_data.setMatchDataId(8, "0");
                        app_data.setMatchDataId(9, "0");
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
                        RocketShipLevel3Hatch = "0";
                        app_data.setMatchDataId(10, "1");
                        app_data.setMatchDataId(11, "0");
                        rocketHatch3.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel3Hatch == "0") {
                        RocketShipLevel3Hatch = "1";
                        app_data.setMatchDataId(10, "0");
                        app_data.setMatchDataId(11, "1");
                        rocketHatch3.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel3Hatch = "";
                        app_data.setMatchDataId(10, "0");
                        app_data.setMatchDataId(11, "0");
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
                        RocketShipLevel1Cargo = "0";
                        app_data.setMatchDataId(16, "1");
                        app_data.setMatchDataId(17, "0");
                        rocketCargo1.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel1Cargo == "0") {
                        RocketShipLevel1Cargo = "1";
                        app_data.setMatchDataId(16, "0");
                        app_data.setMatchDataId(17, "1");
                        rocketCargo1.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel1Cargo = "";
                        app_data.setMatchDataId(16, "0");
                        app_data.setMatchDataId(17, "0");
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
                        RocketShipLevel2Cargo = "0";
                        app_data.setMatchDataId(18, "1");
                        app_data.setMatchDataId(19, "0");
                        rocketCargo2.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel2Cargo == "0") {
                        RocketShipLevel2Cargo = "1";
                        app_data.setMatchDataId(18, "0");
                        app_data.setMatchDataId(19, "1");
                        rocketCargo2.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel2Cargo = "";
                        app_data.setMatchDataId(18, "0");
                        app_data.setMatchDataId(19, "0");
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
                        RocketShipLevel3Cargo = "0";
                        app_data.setMatchDataId(20, "1");
                        app_data.setMatchDataId(21, "0");
                        rocketCargo3.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (RocketShipLevel3Cargo == "0") {
                        RocketShipLevel3Cargo = "1";
                        app_data.setMatchDataId(20, "0");
                        app_data.setMatchDataId(21, "1");
                        rocketCargo3.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        RocketShipLevel3Cargo = "";
                        app_data.setMatchDataId(20, "0");
                        app_data.setMatchDataId(21, "0");
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
                        CargoShipFrontHatch = "0";
                        app_data.setMatchDataId(12, "1");
                        app_data.setMatchDataId(13, "0");
                        hatchFront.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipFrontHatch == "0") {
                        CargoShipFrontHatch = "1";
                        app_data.setMatchDataId(12, "0");
                        app_data.setMatchDataId(13, "1");
                        hatchFront.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipFrontHatch = "";
                        app_data.setMatchDataId(12, "1");
                        app_data.setMatchDataId(13, "0");
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
                        CargoShipFrontCargo = "0";
                        app_data.setMatchDataId(22, "1");
                        app_data.setMatchDataId(23, "0");
                        cargoFront.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipFrontCargo == "0") {
                        CargoShipFrontCargo = "1";
                        app_data.setMatchDataId(22, "0");
                        app_data.setMatchDataId(23, "1");
                        cargoFront.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipFrontCargo = "";
                        app_data.setMatchDataId(22, "0");
                        app_data.setMatchDataId(23, "0");
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
                        CargoShipSideHatch = "0";
                        app_data.setMatchDataId(14, "1");
                        app_data.setMatchDataId(15, "0");
                        hatchSide.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipSideHatch == "0") {
                        CargoShipSideHatch = "1";
                        app_data.setMatchDataId(15, "0");
                        app_data.setMatchDataId(15, "1");
                        hatchSide.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipSideHatch = "";
                        app_data.setMatchDataId(14, "0");
                        app_data.setMatchDataId(15, "0");
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
                        CargoShipSideCargo = "0";
                        app_data.setMatchDataId(22, "1");
                        app_data.setMatchDataId(23, "0");
                        cargoSide.setBackgroundColor(getResources().getColor(R.color.green));
                    } else if (CargoShipSideCargo == "0") {
                        CargoShipSideCargo = "1";
                        app_data.setMatchDataId(22, "0");
                        app_data.setMatchDataId(23, "1");
                        cargoSide.setBackgroundColor(getResources().getColor(R.color.red));
                    } else {
                        CargoShipSideCargo = "";
                        app_data.setMatchDataId(22, "0");
                        app_data.setMatchDataId(23, "0");
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
                //TODO: make sure to save the data
                //make sure to pass on the data.
                app_data.setMatchDataId(5, HasCrossedHabLine);
                app_data.setGame_state(getString(R.string.teleop));
                Intent startIntent = new Intent(getApplicationContext(), GameId1.class);
                startActivity(startIntent);
            }
        });

    }
}
