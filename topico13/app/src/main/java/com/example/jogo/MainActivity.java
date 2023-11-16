package com.example.jogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jogo.controller.ActivityRedirector;
import com.example.jogo.view.PlayActivity;
import com.example.jogo.view.ScoresActivity;
import com.example.jogo.view.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityRedirector.redirectButtonToActivity(this, findViewById(R.id.btnPlay), PlayActivity.class);
        ActivityRedirector.redirectButtonToActivity(this, findViewById(R.id.btnScore), ScoresActivity.class);
        ActivityRedirector.redirectButtonToActivity(this, findViewById(R.id.btnSettings), SettingsActivity.class);
    }

}