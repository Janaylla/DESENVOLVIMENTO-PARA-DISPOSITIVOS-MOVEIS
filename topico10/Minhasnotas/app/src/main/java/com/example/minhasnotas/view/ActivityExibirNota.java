package com.example.minhasnotas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minhasnotas.MainActivity;
import com.example.minhasnotas.R;
import com.example.minhasnotas.controller.NotaController;
import com.example.minhasnotas.model.Nota;
import com.example.minhasnotas.model.NotaDAO;

public class ActivityExibirNota extends AppCompatActivity {

    private EditText textViewTitulo;
    private EditText editTextTexto;
    private Button buttonSalvar;
    private Button buttonCancelar;
    private NotaController notaController;
    private int idNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_nota);

        notaController = new NotaController(this);

        textViewTitulo = findViewById(R.id.textViewTitulo);
        editTextTexto = findViewById(R.id.editTextTexto);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonCancelar = findViewById(R.id.buttonCancelar);
        Intent intentStart = getIntent();
        idNota = intentStart.getIntExtra("id_nota", 0);
        if (idNota != 0) {
            Nota nota = notaController.getNota(idNota);
            textViewTitulo.setText(nota.getTitulo());
            editTextTexto.setText(nota.getTexto());
            buttonSalvar.setText("Editar");
        }

        buttonSalvar.setOnClickListener(this::onClickBtnSalvar);
        buttonCancelar.setOnClickListener(this::onClickBtnCancelar);
    }

    private void onClickBtnSalvar(View view) {
        String titulo = textViewTitulo.getText().toString();
        String texto = editTextTexto.getText().toString();
        Nota nota = new Nota(idNota, titulo, texto);
        if (nota.getId() != 0) {
            notaController.atualizarNota(nota);
        } else {
            notaController.cadastrarNovaNota(nota);
        }
        voltar();
    }
    private void onClickBtnCancelar(View view) {
        voltar();
    }
    private void voltar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}