package com.wcr.wafflesscoutingapp;

import android.app.Application;

public class GlobalData extends Application {
    private String scout_name_first = "";
    private String scout_name_last = "";
    private int game_index;

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
}
