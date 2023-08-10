package com.example.activitymessagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aactivity);
    }
    public void sendMensagem(View view) {
        Intent intent = new Intent(this, BActivity.class);
        EditText editText = (EditText) findViewById(R.id.editMensagem);
        String mensagem = editText.getText().toString();
        intent.putExtra("mensagem", mensagem);
        startActivity(intent);
    }
}