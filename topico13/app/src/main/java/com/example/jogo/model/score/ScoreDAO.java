package com.example.jogo.model.score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ScoreDAO {
    private SQLiteDatabase database;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COIN = "coin";
    private static final String COLUMN_TIME_SECONDS = "time";
    private static final String COLUMN_LEVEL = "level";
    private static final String TABLE_SCORE = "score";
    private static final String DB_SCORE = "scores";

    public ScoreDAO(Context context) {
        database = context.openOrCreateDatabase(DB_SCORE, context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE_SCORE +
                "(" +
                COLUMN_ID +
                " INTEGER PRIMARY KEY, " +
                COLUMN_COIN +
                " INTEGER NOT NULL, " +
                COLUMN_TIME_SECONDS +
                " INTEGER NOT NULL, " +
                COLUMN_LEVEL +
                " INTEGER NOT NULL)");
    }

    public Score insert(Score score) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_COIN, score.getCoin());
        values.put(COLUMN_TIME_SECONDS, score.getTimeSeconds());
        values.put(COLUMN_LEVEL, score.getLevel());

        database.insert(TABLE_SCORE, null, values);
        return score;
    }

    public Score update(Score score) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_COIN, score.getCoin());
        values.put(COLUMN_TIME_SECONDS, score.getTimeSeconds());
        values.put(COLUMN_LEVEL, score.getLevel());

        String whereClause = COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(score.getId())};

        database.update(TABLE_SCORE, values, whereClause, whereArgs);
        return score;
    }

    public void delete(int id) {
        String idString = String.valueOf(id);
        database.delete(TABLE_SCORE, COLUMN_ID + " = ?", new String[]{idString});
    }

    public Score getById(int scoreId) {
        String query = "SELECT * FROM " + TABLE_SCORE + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(scoreId)});

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(+cursor.getColumnIndex(COLUMN_ID));
            int coin = cursor.getInt(+cursor.getColumnIndex(COLUMN_COIN));
            int time = cursor.getInt(+cursor.getColumnIndex(COLUMN_TIME_SECONDS));
            int level = cursor.getInt(+cursor.getColumnIndex(COLUMN_LEVEL));

            Score score = new Score(id, coin, time, level);
            return score;
        }
        return null;
    }

    public ArrayList<Score> getAll(int limit) {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_SCORE + " LIMIT " + limit + ";", null);
        cursor.moveToFirst();
        ArrayList<Score> scoreList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(+cursor.getColumnIndex(COLUMN_ID));
            int coin = cursor.getInt(+cursor.getColumnIndex(COLUMN_COIN));
            int time = cursor.getInt(+cursor.getColumnIndex(COLUMN_TIME_SECONDS));
            int level = cursor.getInt(+cursor.getColumnIndex(COLUMN_LEVEL));

            Score score = new Score(id, coin, time, level);
            scoreList.add(score);
            cursor.moveToNext();
        }
        return scoreList;
    }
}
