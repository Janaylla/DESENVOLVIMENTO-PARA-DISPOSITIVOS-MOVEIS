package com.example.layouts;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Atividade3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade3);
        Locale currentLocale = getResources().getConfiguration().getLocales().get(0);

        String language = currentLocale.getLanguage();

        if(language == "en"){
            ImageView image = (ImageView) findViewById(R.id.imgPt);
            this.changeImageBlack(image);
        }else{
            ImageView image = (ImageView) findViewById(R.id.imgEn);
            this.changeImageBlack(image);
        }

    }
    private void changeImageBlack(ImageView imageView ){


        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);
        imageView.invalidate();
    }
}