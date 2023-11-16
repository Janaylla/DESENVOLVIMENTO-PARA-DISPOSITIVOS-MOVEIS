package com.example.jogo.model.game;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class Screen {
    private int width;
    private int height;
    Screen(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
