package com.example.mypaint;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayDeque;
import java.util.Deque;

public class PaintLayerHistory {
    private Deque<PaintLayer> listPaintLayer;
    private Deque<PaintLayer> subsequent;
    private PaintLayer current;
    PaintLayerHistory(){
        listPaintLayer = new ArrayDeque<>();
        subsequent = new ArrayDeque<>();
        PaintLayer current2 = new PaintLayer();


        listPaintLayer.push(current2);

        current = new PaintLayer(current2);
        current.setStrokeWidth(2); // Largura da borda
        current.setColor(Color.valueOf(Color.BLACK));
    }

    public void add(){
        listPaintLayer.push(current);
        if(!subsequent.isEmpty()){
            subsequent.clear();
        }
        current = new PaintLayer(current);
    }
    public PaintLayer getCurrent(){
        return current;
    }
    public Deque<PaintLayer> getPaintLayerList(){
        return listPaintLayer;
    }
    void undo(){
        PaintLayer paintLayerUndo = listPaintLayer.pop();
        subsequent.push(paintLayerUndo);
        current = new PaintLayer(listPaintLayer.peek());
    }
    void redo(){
        PaintLayer paintLayerRedo = subsequent.pop();
        listPaintLayer.push(paintLayerRedo);
        current = new PaintLayer(listPaintLayer.peek());
    }

    void clear(){
        listPaintLayer.clear();
        subsequent.clear();
    }
    boolean isEmptyPrevious(){
        return listPaintLayer.isEmpty();
    }
    boolean isEmptySubsequent(){
        return subsequent.isEmpty();
    }
}
