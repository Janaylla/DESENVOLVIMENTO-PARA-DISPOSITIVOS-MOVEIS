package com.example.jogo.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class ActivityRedirector {
    public static void redirectButtonToActivity(final Activity activity, Button button, final Class<?> targetActivity) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, targetActivity);
                activity.startActivity(intent);
            }
        });
    }

    public static void redirectToActivity(final Activity activity, final Class<?> targetActivity) {
        Intent intent = new Intent(activity, targetActivity);
        activity.startActivity(intent);
    }
}
