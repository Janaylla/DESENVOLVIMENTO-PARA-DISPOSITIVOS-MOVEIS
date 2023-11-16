package com.example.jogo.model.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

public class Ball {
    private PointF position;
    private PointF velocity;
    private Path path;
    private Paint paint;

    private int radius = 20;
    private int gap = 200;
    private Screen screen;
    private boolean collidedBottom = false;
    private int speed = 4;
    private int maxSpeedX = 3;

    int color;
    public Ball(Screen screen, int color) {
        this.screen = screen;
        calculateStartingPosition();
        velocity = new PointF(0, +speed);// Velocidade inicial (0, 0)
        paint = new Paint();
        paint.setColor(color); // Cor vermelha
        paint.setStyle(Paint.Style.FILL);

        path = new Path();

        path.addCircle(position.x, position.y, radius, Path.Direction.CW);
        this.color = color;

    }

    private void calculateStartingPosition() {
        float x = (screen.getWidth() + radius) / 2;
        float y = screen.getHeight() - radius - gap;
        PointF pointF = new PointF(x, y);
        position = pointF;
    }

    private void checkCollisionWithScreen() {
        if (checkCollidedLeft() && velocity.x < 0) {
            velocity.x = -velocity.x;
            position.x = 0;
        } else if (checkCollidedRight() && velocity.x > 0) {
            velocity.x = -velocity.x;
            position.x = screen.getWidth() - radius * 2;
        }
        if (checkCollidedTop() && velocity.y < 0) {
            velocity.y = -velocity.y;
            position.y = 0;
        } else if (checkCollidedBottom()) {
            velocity.y = 0;
            velocity.x = 0;
            collidedBottom = true;
        }
    }

    private boolean checkCollidedLeft() {
        return position.x < 0;
    }

    private boolean checkCollidedRight() {
        return position.x > screen.getWidth() - radius * 2;
    }

    private boolean checkCollidedTop() {
        return position.y < 0;
    }

    private boolean checkCollidedBottom() {
        return position.y + radius * 2 > screen.getHeight();
    }

    public void run(Bat bat) {
        float newX = position.x + velocity.x;
        float newY = position.y + velocity.y;
        setPosition(newX, newY);
        checkCollisionWithScreen();
        checkCollisionWithBat(bat);
    }

    public void checkCollisionWithBat(Bat bat) {
        float ballLeft = position.x;
        float ballRight = position.x + 2 * radius;
        float ballBottom = position.y + 2 * radius;
        float ballCenter = position.x + radius;
        float batLeft = bat.getPosition().x;
        float batRight = bat.getPosition().x + bat.getWidth();
        float batTop = bat.getPosition().y;
        float batCenter =  bat.getPosition().x + bat.getWidth() / 2;

        if (ballRight >= batLeft && ballLeft <= batRight && velocity.y > 0 && ballBottom >= batTop) {
            if (ballBottom > batTop) {
                //Garantido que a bola não vai sobrepor a bat
                position.y = bat.getPosition().y - 2 * radius;
            }
            velocity.x = ((batCenter - ballCenter) * maxSpeedX) / (batCenter - batRight);
            velocity.y = -speed;
        }
    }


    public PointF getPosition() {
        return position;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
        path.reset();
        path.addCircle(x, y, radius, Path.Direction.CW);
    }

    public boolean isCollidedBottom() {
        return collidedBottom;
    }


    public PointF getVelocity() {
        return velocity;
    }

    public void setVelocity(float vx, float vy) {
        velocity.set(vx, vy);
    }

    public void draw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }
    public int getRadius() {
        return radius;
    }
}
