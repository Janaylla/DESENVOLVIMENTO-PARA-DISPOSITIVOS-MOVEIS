package com.example.jogo.model.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;

public class Coin {
    private PointF position;
    private Path path;
    private Paint paint;
    private int radius = 10;
    public Coin(float x, float y, int color) {
        position = new PointF(x, y);
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        path = new Path();
        path.addCircle(position.x, position.y, radius, Path.Direction.CW);
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
    public PointF getPosition() {
        return position;
    }
    public int getRadius() {
        return radius;
    }
}
