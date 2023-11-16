package com.example.jogo.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;


import com.example.jogo.R;

public class PreferenceColors {
    SharedPreferences sharedPreferences;
    private static final String PREFERENCES_FILE_NAME = "com.example.jogo.preferences";

    private static final String KEY_COINS = "coinsColor";
    private static final String KEY_BALL = "ballColor";
    private static final String KEY_BAT = "batColor";

    public String coinsColorHex;
    public String ballColorHex;
    public String batColorHex;

    public static enum OptionType {
        COINS(KEY_COINS),
        BALL(KEY_BALL),
        BAT(KEY_BAT);

        private final String key;

        OptionType(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    public PreferenceColors(Context context) {
        this.sharedPreferences = getSharedPreferences(context);
        ballColorHex = sharedPreferences.getString(KEY_BALL, "#FFFFFF");
        coinsColorHex = sharedPreferences.getString(KEY_COINS, "#FFFFFF");
        batColorHex = sharedPreferences.getString(KEY_BAT, "#FFFFFF");
    }

    public void save() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COINS, coinsColorHex);
        editor.putString(KEY_BALL, ballColorHex);
        editor.putString(KEY_BAT, batColorHex);
        editor.apply();
    }


    public int getCoinsColorInt() {
        return Color.parseColor(coinsColorHex);
    }

    public int getBallColorInt() {
        return Color.parseColor(ballColorHex);
    }

    public int getBatColorInt() {
        return Color.parseColor(batColorHex);
    }

    public void setColorHex(OptionType optionType, String colorHex) {
        switch (optionType) {
            case COINS:
                coinsColorHex = colorHex;
                break;
            case BALL:
                ballColorHex = colorHex;
                break;
            case BAT:
                batColorHex = colorHex;
                break;
        }
    }

    public String getColorHex(OptionType optionType) {
        switch (optionType) {
            case COINS:
                return coinsColorHex;
            case BALL:
                return ballColorHex;
            case BAT:
                return batColorHex;
            default:
                return "";
        }
    }

    public int getColorInt(OptionType optionType) {
        switch (optionType) {
            case COINS:
                return Color.parseColor(coinsColorHex);
            case BALL:
                return Color.parseColor(ballColorHex);
            case BAT:
                return Color.parseColor(batColorHex);
            default:
                return Color.WHITE;
        }
    }
}
