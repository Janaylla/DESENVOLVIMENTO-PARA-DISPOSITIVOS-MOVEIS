package com.example.jogo.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.jogo.R;
import com.example.jogo.controller.PreferenceColors;

public class ArrayAdapterSettingsColors extends ArrayAdapter<String> {

    private PreferenceColors preferenceColors;
    private SettingsActivity settingsActivity;

    public ArrayAdapterSettingsColors(SettingsActivity settingsActivity,
                                      int grid_setting_option_layout,
                                      String[] scores,
                                      PreferenceColors preferenceColors) {
        super(settingsActivity, grid_setting_option_layout, scores);
        this.preferenceColors = preferenceColors;
        this.settingsActivity = settingsActivity;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.grid_setting_color_layout, parent, false);
        String corHexadecimal = getItem(position);
        Drawable drawable = view.getBackground();
        int colorInt = Color.parseColor(corHexadecimal);
        int colorStroke = Color.BLACK;

        if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            gradientDrawable.setColor(colorInt);
            if (settingsActivity.getSelectedOption() != null) {
                int color = preferenceColors.getColorInt(settingsActivity.getSelectedOption());
                if (color == colorInt) {
                    colorStroke = Color.WHITE;
                }

            }
            gradientDrawable.setStroke(12, colorStroke);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settingsActivity.getSelectedOption() != null){
                    preferenceColors.setColorHex(settingsActivity.getSelectedOption(), corHexadecimal);
                    notifyDataSetInvalidated();
                }
            }
        });

        return view;
    }

}