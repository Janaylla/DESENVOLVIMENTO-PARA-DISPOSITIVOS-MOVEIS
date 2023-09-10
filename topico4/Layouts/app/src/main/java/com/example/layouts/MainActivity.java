package com.example.layouts;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAtividade1 = (Button) findViewById(R.id.atividade1);
        Button btnAtividade2 = (Button) findViewById(R.id.atividade2);
        Button btnAtividade3 = (Button) findViewById(R.id.atividade3);

        btnAtividade1.setOnClickListener(onChangeActivity(Atividade1.class));
        btnAtividade2.setOnClickListener(onChangeActivity(Atividade2.class));
        btnAtividade3.setOnClickListener(onChangeActivity(Atividade3.class));
    }
    private View.OnClickListener onChangeActivity(Class Atividade){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Atividade);
                startActivity(intent);
            }
        };
    }
    private Context getContext(){
        return this;
    }
}