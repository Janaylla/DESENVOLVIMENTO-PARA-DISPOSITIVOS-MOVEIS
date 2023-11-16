package com.example.jogo.controller;

import android.content.Context;

import com.example.jogo.model.score.Score;
import com.example.jogo.model.score.ScoreDAO;

import java.util.ArrayList;

public class ScoreController {
    Context context;
    ScoreDAO scoreDAO;

    public ScoreController(Context context) {
        this.context = context;
        scoreDAO = new ScoreDAO(context);
    }

    public Score register(Score score) {
        return scoreDAO.insert(score);
    }

    public Score update(Score score) {
        return scoreDAO.update(score);
    }

    public void delete(int id) {
        scoreDAO.delete(id);
    }

    public Score getById(int scoreId) {
        return scoreDAO.getById(scoreId);
    }

    public ArrayList<Score> getAll(int limit) {
        return scoreDAO.getAll(limit);
    }
}