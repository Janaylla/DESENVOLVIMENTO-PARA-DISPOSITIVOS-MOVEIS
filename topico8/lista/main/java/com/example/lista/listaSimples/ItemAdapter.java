package com.example.lista.listaSimples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lista.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private int resource;
    private ArrayList<Item> itemList;
    public ItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.itemList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(resource, parent, false);
        Item item = getItem(position);

        if(item != null){
            TextView texto = view.findViewById(R.id.LsTexto);
            texto.setText(item.getNome());

            ImageButton deleteButton = view.findViewById(R.id.LsDelete);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeItem(position);
                }
            });

        }
        return view;
    }
    private void removeItem(int position) {
        itemList.remove(position);
        notifyDataSetChanged();
    }

}
