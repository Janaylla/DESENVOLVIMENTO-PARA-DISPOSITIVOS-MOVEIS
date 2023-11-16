package com.example.jogo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.jogo.R;
import com.example.jogo.model.game.Game;

public class PlayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        showGameFragment();
    }

    public void showGameOverFragment(Game game, int level, int coins, String time) {
        game.destroyDrawingCache();
        GameOverFragment gameOverFragment = GameOverFragment.newInstance(level, coins, time);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.gameLayout, gameOverFragment)
                .commit();
    }
    public void showGameFragment() {
        SharedPreferences sharedPreferences = this.getPreferences(MODE_PRIVATE);
        GameFragment gameOverFragment = GameFragment.newInstance(this, sharedPreferences);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.gameLayout, gameOverFragment)
                .commit();
    }

}