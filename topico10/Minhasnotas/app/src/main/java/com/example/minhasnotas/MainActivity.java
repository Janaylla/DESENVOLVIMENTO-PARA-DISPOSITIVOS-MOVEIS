package com.example.minhasnotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.minhasnotas.controller.NotaController;
import com.example.minhasnotas.model.Nota;
import com.example.minhasnotas.view.ActivityExibirNota;
import com.example.minhasnotas.view.ArrayAdapterNotas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listNota);
        NotaController notaController = new NotaController(this);
        ArrayList<Nota> notas = notaController.getListNotas();

        ArrayAdapterNotas appAdapter = new ArrayAdapterNotas(
                this,
                R.layout.item_lista_nota,
                notas,
                this,
                notaController);
        listView.setAdapter(appAdapter);
        FloatingActionButton novoBotao = findViewById(R.id.btnAdd); // Substitua "seu_botao_id" pelo ID do seu botão

        novoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                novaNota(view);
            }
        });

    }

    private void novaNota(View v) {
        Intent intent = new Intent(this, ActivityExibirNota.class);
        intent.putExtra("id_nota", 0);
        startActivity(intent);
    }


}