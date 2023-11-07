package com.example.lista.listaSimples;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lista.R;

import java.util.ArrayList;

public class ListaSimplesActivity extends AppCompatActivity {

    private EditText editText;
    private ImageButton addButton;
    private ListView listView;
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_simples);

        editText = (EditText)  findViewById(R.id.LsEditaTexto);
        addButton = (ImageButton) findViewById(R.id.LsAddTexto);
        listView = (ListView) findViewById(R.id.LsView);

        adapter = new ItemAdapter(this, R.layout.activity_texto_simples, itemArrayList);

        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = editText.getText().toString().trim();
                if(!texto.isEmpty()){
                    addItem(new Item(texto));
                }
            }
        });
    }
    private void addItem(Item item){
        itemArrayList.add(item);
        adapter.notifyDataSetChanged();
        editText.getText().clear();
    }
}