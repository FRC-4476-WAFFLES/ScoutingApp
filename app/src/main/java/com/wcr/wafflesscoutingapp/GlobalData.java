package com.wcr.wafflesscoutingapp;

import android.app.Application;

public class GlobalData extends Application {
    private String scout_name_first;
    private String scout_name_second;
    private int game_index;

    public String get_Scout_name_first() {
        return scout_name_first;
    }

    public void set_Scout_name_first(String scout_name_first) {
        this.scout_name_first = scout_name_first;
    }

    public String get_Scout_name_second() {
        return scout_name_second;
    }

    public void set_Scout_name_second(String scout_name_second) {
        this.scout_name_second = scout_name_second;
    }

    public int get_Game_index() {
        return game_index;
    }

    public void set_Game_index(int game_index) {
        this.game_index = game_index;
    }
}
