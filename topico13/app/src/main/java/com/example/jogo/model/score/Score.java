package com.example.jogo.model.score;

public class Score {
    private int id;
    private int coin;
    private int timeSeconds;
    private int level;

    public Score(int id, int coin, int timeSeconds, int level) {
        this.id = id;
        this.coin = coin;
        this.timeSeconds = timeSeconds;
        this.level = level;
    }

    public Score(int coin, int timeSeconds, int level) {
        this.coin = coin;
        this.timeSeconds = timeSeconds;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoin() {
        return coin;
    }


    public int getTimeSeconds() {
        return timeSeconds;
    }

    public String getTimeToString() {
        int minutes = timeSeconds / 60;
        int seconds = timeSeconds % 60;
        int totalSeconds = seconds + minutes * 60;

        String runningTimeToString = String.format("%02d:%02d", minutes, totalSeconds);
        return runningTimeToString;
    }


    public int getLevel() {
        return level;
    }

}
