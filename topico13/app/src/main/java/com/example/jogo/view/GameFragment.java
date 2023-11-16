package com.example.jogo.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jogo.R;
import com.example.jogo.model.game.Game;


public class GameFragment extends Fragment {
    private PlayActivity playActivity;
    private SharedPreferences sharedPreferences;

    TextView textCoins;
    TextView textLevel;
    TextView textTime;
    Game game;
    private Handler handler = new Handler(Looper.getMainLooper());
    public GameFragment(PlayActivity playActivity, SharedPreferences sharedPreferences) {
        this.playActivity = playActivity;
        this.sharedPreferences = sharedPreferences;
    }

    public static GameFragment newInstance(PlayActivity playActivity, SharedPreferences sharedPreferences) {
        GameFragment fragment = new GameFragment(playActivity, sharedPreferences);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        game = new Game(playActivity, null, this, sharedPreferences);
        FrameLayout gameLayout = view.findViewById(R.id.gameLayout);
        gameLayout.addView(game);
        textCoins = view.findViewById(R.id.textCoins);
        textLevel = view.findViewById(R.id.textLevel);
        textTime = view.findViewById(R.id.textTime);
        setTextCoin(0);
        setTextLevel(1);
        setTextTime("00:00");
        return view;
    }
    public void setTextCoin(int cont){
        textCoins.setText("Moedas: " + cont);
    }
    public void setTextLevel(int level){
        textLevel.setText("Level: " + level);
    }
    public void setTextTime(String time){
        textTime.setText("Tempo: " + time);
    }
}