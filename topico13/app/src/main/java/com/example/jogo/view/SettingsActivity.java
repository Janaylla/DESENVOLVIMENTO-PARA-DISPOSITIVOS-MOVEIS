package com.example.jogo.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.jogo.MainActivity;
import com.example.jogo.R;
import com.example.jogo.controller.ActivityRedirector;
import com.example.jogo.controller.PreferenceColors;
import com.example.jogo.controller.PreferenceColors.OptionType;

public class SettingsActivity extends AppCompatActivity {

    private GridView gridViewColors;
    private SharedPreferences sharedPreferences;
    private PreferenceColors preferenceColors;

    private OptionType selectedOption;
    Button btnSettingOptionBall;
    Button btnSettingOptionBat;
    Button btnSettingOptionCoins;
    ArrayAdapterSettingsColors arrayAdapterSettingsColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferenceColors = new PreferenceColors(this);
        gridViewColors = findViewById(R.id.gridViewColors);

        btnSettingOptionBall = findViewById(R.id.btnSettingOptionBall);
        btnSettingOptionBat = findViewById(R.id.btnSettingOptionBat);
        btnSettingOptionCoins = findViewById(R.id.btnSettingOptionCoins);
        Button btnSettingSave = findViewById(R.id.btnSettingSave);
        setOnClickListener(btnSettingOptionBall, this::onBtnSettingOptionBallClick);
        setOnClickListener(btnSettingOptionBat, this::onBtnSettingOptionBatClick);
        setOnClickListener(btnSettingOptionCoins, this::onBtnSettingOptionCoinsClick);
        setOnClickListener(btnSettingSave, this::onBtnSave);
        setupColors();
    }

    private void setupColors() {
        String[] colors = getResources().getStringArray(R.array.color_options);

        arrayAdapterSettingsColors = new ArrayAdapterSettingsColors(
                this,
                R.layout.grid_setting_color_layout,
                colors,
                preferenceColors);
        gridViewColors.setAdapter(arrayAdapterSettingsColors);

    }

    private void setOnClickListener(Button btnSetting, View.OnClickListener function) {
        btnSetting.setOnClickListener(function);
    }

    public void setSelectedOption(OptionType option, Button btn) {
        selectedOption = option;
        updateUI();
        btn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        btn.setTextColor(ContextCompat.getColor(this, R.color.black));
        arrayAdapterSettingsColors.notifyDataSetInvalidated();
    }

    private void updateUI() {
        btnSettingOptionBall.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        btnSettingOptionBat.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        btnSettingOptionCoins.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        btnSettingOptionBall.setTextColor(ContextCompat.getColor(this, R.color.white));
        btnSettingOptionBat.setTextColor(ContextCompat.getColor(this, R.color.white));
        btnSettingOptionCoins.setTextColor(ContextCompat.getColor(this, R.color.white));
    }


    public void onBtnSettingOptionBallClick(View view) {
        setSelectedOption(PreferenceColors.OptionType.BALL, btnSettingOptionBall);
    }

    public void onBtnSettingOptionBatClick(View view) {
        setSelectedOption(PreferenceColors.OptionType.BAT, btnSettingOptionBat);
    }

    public void onBtnSettingOptionCoinsClick(View view) {
        setSelectedOption(PreferenceColors.OptionType.COINS, btnSettingOptionCoins);
    }
    public void onBtnSave(View view) {
        preferenceColors.save();
        ActivityRedirector.redirectToActivity(this, MainActivity.class);
    }


    public OptionType getSelectedOption() {
        return selectedOption;
    }

}
