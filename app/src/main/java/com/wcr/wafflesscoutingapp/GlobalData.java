package com.wcr.wafflesscoutingapp;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.wcr.wafflesscoutingapp.GameId1.GameId1Before;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class GlobalData extends Application {
    private static final String TAG = "GlobalData";
    private String scout_name_first = "";
    private String scout_name_last = "";
    private int game_index;
    //0: id0
    //1: id1
    //2: id2
    //3: id3
    private String game_state;
    //1: pregame
    //2: sandstorm
    //3: teleoperated
    //4: endgame
    private String event;

    public String current_file;

    public String content_text;


    //map of indexes in in LEGEND.ods
    String[] matchData = new String[45];


    public String get_Scout_name_first() {
        return scout_name_first;
    }

    public void set_Scout_name_first(String scout_name_first) {
        this.scout_name_first = scout_name_first;
    }

    public String get_Scout_name_last() {
        return scout_name_last;
    }

    public void set_Scout_name_last(String scout_name_last) {
        this.scout_name_last = scout_name_last;
    }

    public int get_Game_index() {
        return game_index;
    }

    public void set_Game_index(int game_index) {
        this.game_index = game_index;
    }

    public String getGame_state() {
        return game_state;
    }

    public void setGame_state(String game_state) {
        this.game_state = game_state;
    }
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setMatchDataId(int id, String content, Activity a){
        matchData[id] = content;
        if(matchData[1] != null && matchData[0] != null){
            save_to_csv(a);
        }

    }
    public String getMatchDataId(int id){
        return matchData[id];
    }

    public void reset_all_match_data(){
        game_state = "0";
        current_file = "";
        matchData = new String[45];
    }

    private void save_to_csv(Activity a) {
        if(isWriteExternalStorageGranted()) {
            current_file = "WafflesScoutingAppData/tmp_save.csv";

            String content = genContentString();

            String state;
            state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File Root = Environment.getExternalStorageDirectory();
                File Dir = new File(Root.getAbsoluteFile() + "/WafflesScoutingAppData");

                if (!Dir.exists()) {
                    Dir.mkdir();
                }
                File file = new File(Dir, "tmp_save.csv");
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    fos.write(content.getBytes());
                    fos.close();
                    Toast.makeText(this, "saved to " + file, Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, "Cannot write to External Storage", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Cannot write to External Storage", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }

    public  boolean isWriteExternalStorageGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Write Permission is granted");
                return true;
            } else {
//                ActivityCompat.requestPermissions(new Activity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                Log.v(TAG,"Write Permission is revoked");
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Write Permission is granted");
            return true;
        }
    }

    public String genContentString(){
        String content = "";
        for (int i = 0; i < matchData.length; i += 1) {
            if (matchData[i] == null) {
                content = content + "0" + ",";
            } else {
                content = content + matchData[i] + ",";
            }
        }
        content_text = content;
        return content;
    }
}
