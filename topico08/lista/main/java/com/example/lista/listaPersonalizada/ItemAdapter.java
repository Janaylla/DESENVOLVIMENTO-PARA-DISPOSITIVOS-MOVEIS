package com.example.lista.listaPersonalizada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lista.R;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private int resource;
    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resource, parent, false);
        Item item = getItem(position);

        if(item != null){
            TextView texto = view.findViewById(R.id.LpTexto);
            ImageView image = view.findViewById(R.id.LpImagem);

            texto.setText(item.getNome());
            image.setImageResource(item.getFoto());
        }
        return view;
    }
}
