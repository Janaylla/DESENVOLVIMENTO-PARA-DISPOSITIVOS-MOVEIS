package com.example.jogo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.jogo.R;
import com.example.jogo.model.score.Score;

import java.util.ArrayList;

public class ArrayAdapterScores extends ArrayAdapter<Score> {

    public ArrayAdapterScores(Context context, int point_list, ArrayList<Score> scores) {
        super(context, point_list, scores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.point_list_item, parent, false);
        Score score = getItem(position);

        TextView textCoins = view.findViewById(R.id.textCoins);
        TextView textLevel = view.findViewById(R.id.textLevel);
        TextView textTime = view.findViewById(R.id.textTime);

        textCoins.setText("Moedas: " + score.getCoin());
        textLevel.setText("Nível: " + score.getLevel());
        textTime.setText("Tempo: " + score.getTimeToString());
        return view;
    }
}
