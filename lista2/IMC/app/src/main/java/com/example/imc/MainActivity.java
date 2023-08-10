package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void btnCalcularOnClick(View v) {
        String nome = ((EditText) findViewById(R.id.txtNome)).getText().toString();
        String peso = ((EditText) findViewById(R.id.txtPeso)).getText().toString();
        String altura = ((EditText) findViewById(R.id.txtAltura)).getText().toString();

        Intent intent = new Intent(this, MostraResultadoIMC.class);
        intent.putExtra("nome", nome);
        intent.putExtra("peso", peso);
        intent.putExtra("altura", altura);
        startActivity(intent);

    }

}