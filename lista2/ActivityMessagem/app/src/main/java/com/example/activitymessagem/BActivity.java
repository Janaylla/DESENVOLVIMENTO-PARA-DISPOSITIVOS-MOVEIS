package com.example.activitymessagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bactivity);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String mensagem = intent.getStringExtra("mensagem");

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.mostraMensagem);
        textView.setText(mensagem);
    }
}