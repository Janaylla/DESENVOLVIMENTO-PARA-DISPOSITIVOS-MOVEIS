package com.example.jogo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.jogo.MainActivity;
import com.example.jogo.R;
import com.example.jogo.controller.ActivityRedirector;
import com.example.jogo.model.game.Game;

public class GameOverFragment extends Fragment {

    TextView textCoins;
    TextView textLevel;
    TextView textTime;
    Button btnMenu;

    public GameOverFragment() {
    }

    public static GameOverFragment newInstance(int level, int coins, String time) {
        GameOverFragment fragment = new GameOverFragment();

        Bundle args = new Bundle();
        args.putInt("level", level);
        args.putInt("coins", coins);
        args.putString("time", time);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_over, container, false);

        // Encontre os elementos no layout XML por ID dentro do fragmento
        textCoins = view.findViewById(R.id.textCoins);
        textLevel = view.findViewById(R.id.textLevel);
        textTime = view.findViewById(R.id.textTime);
        btnMenu = view.findViewById(R.id.btnMenu);

        // Recupere os argumentos passados ao fragmento
        Bundle args = getArguments();
        if (args != null) {
            int level = args.getInt("level", 0);
            int coins = args.getInt("coins", 0);
            String time = args.getString("time", "");

            textCoins.setText("Moedas: " + coins);
            textLevel.setText("Nível: " + level);
            textTime.setText("Tempo: " + time);
        }
        ActivityRedirector.redirectButtonToActivity(getActivity(), btnMenu, MainActivity.class);
        return view;
    }

}