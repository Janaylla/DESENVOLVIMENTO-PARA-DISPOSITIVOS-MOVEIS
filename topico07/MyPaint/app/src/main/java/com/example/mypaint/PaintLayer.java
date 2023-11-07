package com.example.mypaint;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;

public class PaintLayer {
    private Paint paint;
    private Path path;

    private Shape shape;
    private float currentX;
    private float currentY;
    private float startX;
    private float startY;

    private int colorFill;

    private int color;
    private MotionEvent event;
    private int stroke;

    public PaintLayer() {
        this.paint = new Paint();
        this.path = new Path();
        paint.setColor(Color.BLACK);
        shape = Shape.SQUARE;
    }
    public PaintLayer(PaintLayer currentPaint){
        this.paint = new Paint();
        this.path = new Path();
        this.color = currentPaint.color;
        this.colorFill = currentPaint.colorFill;
        this.stroke = currentPaint.stroke;
        this.shape = currentPaint.shape;
    }


    public void setEventAndCoordinator(MotionEvent event){
        this.event = event;
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                setStartCoordinator(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                setCurrentCoordinator(x, y);
                break;
        }
        drawShape();
    }
    private void setStartCoordinator(float startX, float startY){
        this.startX = startX;
        this.startY = startY;
        this.currentX = startX;
        this.currentY = startY;
    }
    private void setCurrentCoordinator(float currentX, float currentY){
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public void setColor(Color color){
        this.color = color.toArgb();
    }

    public void setColorFill(Color color){
        this.colorFill = color.toArgb();
    }
    public void setStroke(int stroke){
        this.stroke = stroke;
    }

    public Paint getPaintDrawing() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        paint.setColor(color);
        return paint;
    }
    public Paint getPaintDrawingFill() {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorFill);
        return paint;
    }

    public Path getPath() {
        return path;
    }
    private void drawShape(){
        switch (shape) {
            case CIRCLE:
                drawCircle();
                break;
            case SQUARE:
                drawSquare();
                break;
            case TRIANGLE:
                drawTriangle();
                break;
            case LINE:
                drawLine();
                break;
            case BRUSH:
                useBrush();
                break;
        }
    }
    private void drawCircle() {
        path.reset();
        path.addOval(startX, startY, currentX, currentY, Path.Direction.CW);
    }
    private void drawSquare() {
        path.reset();
        float x1 = startX > currentX ? currentX : startX;
        float x2 = startX > currentX ? startX : currentX;
        float y1 = startY > currentY ? currentY : startY;
        float y2 = startY > currentY ? startY : currentY;
        path.addRect(x1, y1, x2, y2, Path.Direction.CW);
    }

    private void drawTriangle() {
        path.reset();

        float x1 = startX > currentX ? currentX : startX; // Base do triangulo
        float x2 = startX > currentX ? startX : currentX;
        float y1 = startY;
        float y2 = startY;
        //Topo do triangulo, metade do caminho dos x, pode esta invertido
        //, o topo de y sera onde o usuario deixar o dedo no final
        float x3 = ((x2 - x1) / 2) + x1;
        float y3 = currentY;
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
    }

    private void drawLine() {
        path.reset();
        path.moveTo(startX, startY);
        path.lineTo(currentX, currentY);
    }
    private void useBrush() {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(startX, startY);
                path.lineTo(startX, startY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(currentX, currentY);
                path.lineTo(currentX, currentY);
                break;
        }
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setStrokeWidth(int i) {
        paint.setStrokeWidth(2);
    }
}
