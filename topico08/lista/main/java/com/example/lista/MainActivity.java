package com.example.lista;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lista.listaPersonalizada.ListaPersonalizadaActivity;
import com.example.lista.listaSimples.ListaSimplesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatActivity ListaPersonalizadaActivity;
        switchActivities(R.id.btnListaPersonalizada, ListaPersonalizadaActivity.class);
        switchActivities(R.id.btnListaSala, ListaSimplesActivity.class);
    }

    private void switchActivities(int id, Class classActivity) {
        Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(getApplicationContext(), classActivity);
                startActivity(switchActivityIntent);
            }
        });
    }
}