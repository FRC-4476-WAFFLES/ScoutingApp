package com.wcr.wafflesscoutingapp;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;

public class GlobalData extends Application {
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

    public void setMatchDataId(int id, String content){
        matchData[id] = content;
        if(matchData[1] != null && matchData[0] != null){
            save_to_csv();
        }

    }
    public String getMatchDataId(int id){
        return matchData[id];
    }

    private void reset_all_match_data(){
        game_state = "0";
        current_file = "";
        matchData = new String[45];
    }

    private void save_to_csv(){
        //assemble string
        String dir = "";
        if(game_index == 0){
            dir = getString(R.string.Index_0_Year);
        }else if(game_index == 1){
            dir = getString(R.string.Index_1_Year);
        }else if(game_index == 2){
            dir = getString(R.string.Index_2_Year);
        }else{
            dir = getString(R.string.Index_3_Year);
        }
        dir = dir + "/" +  getEvent();

        //TODO: make sure these list indexes are correct
        String team_number = "9999";
        String match_number = "9999";
        if(matchData[1] != null){
            match_number = matchData[1];
        }
        if (matchData[0] != null) {
            team_number = matchData[0];
        }
        String filename = match_number + "_" + team_number + ".csv";

        current_file = "WafflesScoutingAppData/" + dir + "/" + filename;

        File directoryDownload = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File logDir = new File (directoryDownload, "/WafflesScoutingAppData/" + dir); //Creates a new folder in DOCUMENTS directory
        logDir.mkdirs();
        File file = new File(logDir, filename);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            //outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for (int i = 0; i < matchData.length; i += 1) {
                outputStream.write((matchData[i] + ",").getBytes());

            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
