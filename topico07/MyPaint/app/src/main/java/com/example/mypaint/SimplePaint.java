package com.example.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Iterator;

public class SimplePaint extends View {
    private PaintLayerHistory paintLayersHistory;

    private MainActivity mainActivity;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mainActivity = ((MainActivity) getContext());
        paintLayersHistory = new PaintLayerHistory();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Iterator<PaintLayer> reverseIterator =
                paintLayersHistory.getPaintLayerList().descendingIterator();

        while (reverseIterator.hasNext()) {
            PaintLayer paint = reverseIterator.next();
            canvas.drawPath(paint.getPath(), paint.getPaintDrawingFill());
            canvas.drawPath(paint.getPath(), paint.getPaintDrawing());

        }
        canvas.drawPath(
                paintLayersHistory.getCurrent().getPath(),
                paintLayersHistory.getCurrent().getPaintDrawingFill());
        canvas.drawPath(
                paintLayersHistory.getCurrent().getPath(),
                paintLayersHistory.getCurrent().getPaintDrawing());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        paintLayersHistory.getCurrent().setEventAndCoordinator(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            paintLayersHistory.add();
            mainActivity.hasChanges();
        }
        invalidate();
        return true;
    }

    public void setColor(Color color) {
        paintLayersHistory.getCurrent().setColor(color);
    }

    public void setColorFill(Color color) {
        paintLayersHistory.getCurrent().setColorFill(color);
    }

    public void setShape(Shape shape) {
        paintLayersHistory.getCurrent().setShape(shape);
    }

    public void setStroke(int stroke) {
        paintLayersHistory.getCurrent().setStroke(stroke);
    }

    void undo() {
        paintLayersHistory.undo();
        invalidate();
    }

    void redo() {
        paintLayersHistory.redo();
        invalidate();
    }

    void clear() {
        paintLayersHistory.clear();
        invalidate();
    }

    boolean isEmptyPrevious() {
        return paintLayersHistory.isEmptyPrevious();
    }

    boolean isEmptySubsequent() {
        return paintLayersHistory.isEmptySubsequent();
    }
}
