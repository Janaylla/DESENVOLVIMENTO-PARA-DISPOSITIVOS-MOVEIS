package com.example.minhasnotas.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.minhasnotas.MainActivity;
import com.example.minhasnotas.R;
import com.example.minhasnotas.controller.NotaController;
import com.example.minhasnotas.model.Nota;

import java.util.ArrayList;

public class ArrayAdapterNotas extends ArrayAdapter<Nota> {
    private Context context;
    private NotaController notaController;
    public ArrayAdapterNotas(MainActivity mainActivity,
                             int item_lista,
                             ArrayList<Nota> notas,
                             Context context,
                             NotaController notaController) {
        super(mainActivity, item_lista, notas);
        this.context = context;
        this.notaController = notaController;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        //Inflando layout do item da lista
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_lista_nota, parent, false);
        //Recuperando o objeto ApplicationInfo da posição atual
        Nota nota = getItem(position);
        //Recuperando os componentes do layout
        TextView textViewTitulo = view.findViewById(R.id.titulo);
        textViewTitulo.setText(nota.getTitulo());

        Intent intent = new Intent(this.getContext(), ActivityExibirNota.class);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("id_nota", nota.getId());
                context.startActivity(intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                confirmaExclusao(nota);
                return false;
            }
        });
        return view;
    }
    public void confirmaExclusao(Nota nota) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Excluir nota " + nota.getTitulo());
        builder.setMessage("Você deseja exluir esta nota?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                notaController.excluirNota(nota.getId());
                remove(nota);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
