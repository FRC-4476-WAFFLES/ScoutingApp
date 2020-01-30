package com.wcr.wafflesscoutingapp;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.wcr.wafflesscoutingapp.GameId1.GameId1Before;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GlobalData extends Application {
    private static final String TAG = "GlobalData";
    private BluetoothAdapter mBluetoothAdapter;
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

    public int match = 1;

    public int number_of_matches = 0;

    public String current_file;

    public String content_text;

    private String[] app_config = new String[5];


    //map of indexes in in LEGEND.ods
    //TODO: set the string array length to 28
    String[] matchData = new String[45];


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
        current_file = "";
        //TODO: set the string array length to 28
        matchData = new String[45];
        if(match < number_of_matches){
            match +=1;
        }
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
//                    Toast.makeText(this, "saved to " + file, Toast.LENGTH_SHORT).show();
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

    public  boolean isReadExternalStorageGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
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
        content = content + getLocalBluetoothName();
        content_text = content;
        return content;
    }

    public String getApp_config(int id) {
        return app_config[id];
    }

    public void setApp_config(Activity a, int id, String new_val) {
        this.app_config[id] = new_val;

        if(isWriteExternalStorageGranted()) {
            //predeclarations
            String content = "";
            String state;
            FileOutputStream fos = null;

            //convert array to string to save to file
            for (int i = 0; i < app_config.length; i += 1) {
                if (app_config[i] == null) {
                    content = content + "0" + "\n";
                } else {
                    content = content + app_config[i] + "\n";
                }
            }

            //checking state of storage
            state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File Root = Environment.getExternalStorageDirectory();
                File Dir = new File(Root.getAbsoluteFile() + "/WafflesScoutingAppData");

                if (!Dir.exists()) {
                    Dir.mkdir();
                }
                File file = new File(Dir, "config.txt");

                try {
                    fos = new FileOutputStream(file);
                    fos.write(content.getBytes());
                    fos.close();
//                    Toast.makeText(this, "saved to " + file, Toast.LENGTH_SHORT).show();
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

    public void load_config(Context c){
        if(isReadExternalStorageGranted()) {
            //file input stream stuff
            List<String> lines = new ArrayList<>();
            File Root = Environment.getExternalStorageDirectory();
            File Dir = new File(Root.getAbsoluteFile() + "/WafflesScoutingAppData");
            File file = new File(Dir, "config.txt");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;


                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                app_config = lines.toArray(new String[lines.size()]);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(c, "borked.", Toast.LENGTH_LONG);
            }
        }
    }
    public String getLocalBluetoothName(){
        if(mBluetoothAdapter == null){
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        String name = mBluetoothAdapter.getName();
        if(name == null){
            System.out.println("Name is null!");
            name = mBluetoothAdapter.getAddress();
        }
        return name;
    }

}
