package com.example.jogo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.jogo.R;
import com.example.jogo.controller.ScoreController;
import com.example.jogo.model.score.Score;

import java.util.ArrayList;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        ScoreController scoreController = new ScoreController(this);
        ArrayList<Score> scores = scoreController.getAll(200);

        ListView listView = findViewById(R.id.list_scores);
        ArrayAdapterScores appAdapter = new ArrayAdapterScores(
                this,
                R.layout.point_list_item,
                scores);

        listView.setAdapter(appAdapter);
    }
}