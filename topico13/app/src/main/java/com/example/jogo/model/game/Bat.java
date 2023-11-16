package com.example.jogo.model.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;

public class Bat {
    private PointF position;
    private int velocityX;
    private Path path;
    private Paint paint;
    private int gap = 50;

    private int width = 250;
    private int height = 25;
    private Screen screen;

    int color;
    public Bat(Screen screen, int color) {
        this.screen = screen;
        calculateStartingPosition();
        velocityX = 5;

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        path = new Path();
        this.addRect();
        this.color = color;
    }

    private void calculateStartingPosition() {
        int x = (screen.getWidth() - width) / 2;
        int y = screen.getHeight() - height - gap;
        PointF pointF = new PointF(x, y);
        position = pointF;
    }

    public void checkCollisionWithScreen() {
        boolean collidedLeft = position.x < 0;
        boolean collidedRight = position.x + width > screen.getWidth();
        if (collidedLeft && velocityX < 0) {
            velocityX = -velocityX;
            position.x = 0;
        } else if (collidedRight && velocityX > 0) {
            velocityX = -velocityX;
            position.x = screen.getWidth() - width;
        }
    }
    public void run() {
        float newX = position.x + velocityX;
        setPosition(newX);
        checkCollisionWithScreen();
    }

    public void setVelocity(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                velocityX = -velocityX;
                break;
        }
    }

    public PointF getPosition() {
        return position;
    }

    public void setPosition(float x) {
        position.set(x, position.y);
        addRect();
    }

    private void addRect() {
        path.reset();
        float x1 = position.x;
        float x2 = x1 + width;
        float y1 = position.y;
        float y2 = y1 + height;
        path.addRect(x1, y1, x2, y2, Path.Direction.CW);

    }
    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    public int getWidth() {
        return width;
    }

}
