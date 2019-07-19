package com.wcr.wafflesscoutingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // importing fonts: https://www.youtube.com/watch?v=fB17m3kX-go
    Typeface CooperBlack;
    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainGrid = (GridLayout)findViewById(R.id.mainGrid);
        CooperBlack = Typeface.createFromAsset(this.getAssets(), "fonts/CooperFiveOpti-Black.otf");
        TextView textView2020 = (TextView)findViewById(R.id.textView0);
        textView2020.setTypeface(CooperBlack);
        TextView textView2019 = (TextView)findViewById(R.id.textView1);
        textView2019.setTypeface(CooperBlack);
        TextView textView2018 = (TextView)findViewById(R.id.textView2);
        textView2018.setTypeface(CooperBlack);
        TextView textView2017 = (TextView)findViewById(R.id.textView3);
        textView2017.setTypeface(CooperBlack);
        TextView textGrid = (TextView)findViewById(R.id.textGrid);
        textGrid.setTypeface(CooperBlack);
        //Set Event
        setSingleEvent(mainGrid);
//        setToggleEvent(mainGrid);
    }
//    private void setToggleEvent(GridLayout mainGrid){
//        //Loop all child items of Main Grid
//        for(int i = 0; i<mainGrid.getChildCount(); i++){
//            final CardView cardView = (CardView)mainGrid.getChildAt(i);
//            cardView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view){
//                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1) {
//                        cardView.setBackgroundColor(Color.parseColor("#FF6F00"));
//                    }else{
//                        cardView.setCardBackgroundColor(Color.parseColor("FFFFFF"));
//                    }
//
//                }
//            });
//        }
//    }

    private void setSingleEvent(GridLayout mainGrid) {
        final GlobalData app_data = (GlobalData) getApplicationContext();
        //Loop all child items of Main Grid
        for(int i = 0; i<mainGrid.getChildCount(); i++){
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        cardView.setBackgroundColor(Color.parseColor("#FF6F00"));
                    }else{
                        cardView.setCardBackgroundColor(Color.parseColor("FFFFFF"));
                    }
                    //replace with start new activity code
                    Toast.makeText(MainActivity.this, "Clicked at index " + finalI, Toast.LENGTH_SHORT).show();

                    Intent startIntent = new Intent(getApplicationContext(), ScoutData.class);
                    //show how to pass information to another activity
                    app_data.set_Game_index(finalI);
                    startActivity(startIntent);
                }
            });

        }
    }

}
