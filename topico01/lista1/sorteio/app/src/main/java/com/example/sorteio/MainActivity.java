package com.example.sorteio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btnSotear);
        TextView txtMax = findViewById(R.id.txtMax);
        TextView txtMin =  findViewById(R.id.txtMin);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Random random = new Random();
                    int max = Integer.parseInt(txtMax.getText().toString());
                    int min = Integer.parseInt(txtMin.getText().toString());
                    int diferenca = max - min;
                    if(diferenca <= 0){
                        return;
                    }
                    // Gere um número aleatório entre min e max
                    int resultadoAleatorio = random.nextInt(diferenca) + min;

                    // Encontre o TextView vsResultado pelo seu id
                    TextView resultado = findViewById(R.id.resultado);

                    // Atualize o texto do TextView com o resultado aleatório
                    resultado.setText(String.valueOf(resultadoAleatorio));
            }
        });
    }
}