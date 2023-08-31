package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MostraResultadoIMC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_resultado_imc);
        Intent intent = getIntent();
        String $nome = intent.getStringExtra("nome");
        float peso = Float.parseFloat(intent.getStringExtra("peso"));
        float altura = Float.parseFloat(intent.getStringExtra("altura"));
        float imc = peso / (altura * altura);
        String classificacao;
        if (imc < 18.5) {
            classificacao = "Abaixo do Peso";
        } else if (imc < 24.9) {
            classificacao= "Peso Ideal";
        } else if (imc < 29.9) {
            classificacao = "Levemente Acima do Peso";
        } else if (imc < 34.9) {
            classificacao = "Obesidade Grau I";
        } else if (imc < 39.9) {
            classificacao = "Obesidade Grau II (Severa)";
        } else {
            classificacao = "Obesidade Grau III (Mórbida)";
        }
        TextView labelNome = findViewById(R.id.labelNome);
        TextView labelAltura = findViewById(R.id.labelAltura);
        TextView labelPeso = findViewById(R.id.labelPeso);
        TextView labelIMC = findViewById(R.id.labelIMC);
        TextView labelCategoria = findViewById(R.id.labelCategoria);
        labelNome.setText("Nome: " + $nome);
        labelPeso.setText("Peso: " + peso);
        labelAltura.setText("Altura: " + altura);
        labelIMC.setText("IMC: " + imc);
        labelCategoria.setText("Classificação: " + classificacao);
    }
    public void voltar(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}