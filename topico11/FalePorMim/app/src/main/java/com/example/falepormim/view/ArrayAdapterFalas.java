package com.example.falepormim.view;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.falepormim.MainActivity;
import com.example.falepormim.R;
import com.example.falepormim.controller.FalaController;
import com.example.falepormim.model.Fala;

import java.util.ArrayList;

public class ArrayAdapterFalas extends ArrayAdapter<Fala> {

    public ArrayAdapterFalas(MainActivity mainActivity,
                             int item_lista,
                             ArrayList<Fala> falas) {
        super(mainActivity, item_lista, falas);
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_lista_fala, parent, false);
        Fala fala = getItem(position);
        TextView textViewTitulo = view.findViewById(R.id.titulo);
        textViewTitulo.setText(fala.getTexto());

        return view;
    }
}
