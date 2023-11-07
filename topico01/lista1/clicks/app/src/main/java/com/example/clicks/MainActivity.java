package com.example.clicks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnClick = findViewById(R.id.btnClick);
        TextView txtQuantidadeClicks = findViewById(R.id.txtQuantidadeClicks);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick: ", txtQuantidadeClicks.getText().toString());
                int quantidade = 1 + Integer.parseInt(txtQuantidadeClicks.getText().toString());
                txtQuantidadeClicks.setText(String.valueOf(quantidade));
            }
        });

    }
}