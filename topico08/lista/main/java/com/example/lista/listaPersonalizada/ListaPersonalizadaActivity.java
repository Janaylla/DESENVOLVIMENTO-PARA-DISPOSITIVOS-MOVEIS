package com.example.lista.listaPersonalizada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.lista.R;

import java.util.ArrayList;

public class ListaPersonalizadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personalizada);

        ListView list = (ListView) findViewById(R.id.LpView);
        ArrayList<Item> arrayList = new ArrayList<Item>(
        );
        arrayList.add(new Item(1, "Cachorro", R.drawable.cachorro));
        arrayList.add(new Item(1, "Jardim", R.drawable.gardem));
        arrayList.add(new Item(1, "Feliz", R.drawable.happy));
        arrayList.add(new Item(1, "Patinho", R.drawable.patinho));
        arrayList.add(new Item(1, "Proquinho", R.drawable.porquinho));

        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.activity_imagem_com_legenda, arrayList);
        list.setAdapter(itemAdapter);
    }
}