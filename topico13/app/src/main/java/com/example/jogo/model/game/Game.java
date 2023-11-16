package com.example.jogo.model.game;

import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Looper;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.jogo.controller.PreferenceColors;
import com.example.jogo.controller.ScoreController;
import com.example.jogo.model.score.Score;
import com.example.jogo.view.GameFragment;
import com.example.jogo.view.PlayActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends View {
    private Ball ball;
    private Bat bat;
    private Coins coins;
    private int period = 10;
    private Timer timer;
    private Screen screen;
    private long gameStartTime;
    private int level = 1;
    private PlayActivity playActivity;
    private GameFragment gameFragment;
    private boolean isGameRunning = true;
    private PreferenceColors preferenceColors;

    public Game(PlayActivity playActivity, @Nullable AttributeSet attrs, GameFragment gameFragment, SharedPreferences sharedPreferences) {

        super(playActivity, attrs);
        preferenceColors = new PreferenceColors(playActivity);
        gameStartTime = System.currentTimeMillis();
        screen = new Screen(playActivity);
        ball = new Ball(screen, preferenceColors.getBallColorInt());
        bat = new Bat(screen, preferenceColors.getBatColorInt());
        timer = new Timer();
        coins = new Coins(level, screen, gameFragment, preferenceColors.getCoinsColorInt());
        timer.scheduleAtFixedRate(new MoveTask(), 0, period);
        timer.scheduleAtFixedRate(new FlashCoin(), 0, 750);
        this.playActivity = playActivity;
        this.gameFragment = gameFragment;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bat.setVelocity(event);
        return true;
    }

    private Handler handler = new Handler(Looper.getMainLooper());

    private void showStatusOnScreen() {
        long elapsedTime = System.currentTimeMillis() - gameStartTime;

        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;

        String runningTimeToString = String.format("%02d:%02d", minutes, seconds);
        handler.post(new Runnable() {
            @Override
            public void run() {
                gameFragment.setTextTime(runningTimeToString);
                gameFragment.setTextCoin(coins.getTotalCoinsCollected());
                gameFragment.setTextLevel(level);
            }
        });
    }

    private class MoveTask extends TimerTask {
        @Override
        public void run() {
            if (!isGameRunning) {
                return;
            }
            bat.run();
            ball.run(bat);
            coins.run(ball);
            if (ball.isCollidedBottom()) {
                gameOver();
            }
            if (coins.isEmpty()) {
                nextLevel();
            }
            showStatusOnScreen();
            invalidate();
        }
    }

    private class FlashCoin extends TimerTask {
        @Override
        public void run() {
            coins.changeShow();
        }
    }

    private void gameOver() {

        long elapsedTime = System.currentTimeMillis() - gameStartTime; // Assuming you have a gameStartTime variable.

        int minutes = (int) ((elapsedTime / 1000) / 60);
        int seconds = (int) ((elapsedTime / 1000) % 60);
        int totalSeconds = seconds + minutes * 60;

        String runningTimeToString = String.format("%02d:%02d", minutes, totalSeconds);
        isGameRunning = false;


        ScoreController scoreController = new ScoreController(playActivity);
        Score score = new Score(coins.getTotalCoinsCollected(), totalSeconds, level);
        scoreController.register(score);

        playActivity.showGameOverFragment(this, level, coins.getTotalCoinsCollected(), runningTimeToString);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ball.draw(canvas);
        bat.draw(canvas);
        coins.draw(canvas);
    }

    private void nextLevel() {
        level++;
        coins.generateRandomCoinsInOneThirdOfScreen(level);
    }

}
