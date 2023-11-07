package com.example.minhasnotas.model;

import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class NotaDAO {
    private SQLiteDatabase database;
    private static final String COLUNA_TITULO = "titulo";
    private static final String COLUNA_TEXTO = "texto";
    private static final String COLUNA_ID = "id";
    private static final String TABELA_NOTAS = "notas";
    private static final String DB_NOTAS = "notas";

    public NotaDAO(Context context) {
        database = context.openOrCreateDatabase(DB_NOTAS, context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABELA_NOTAS +
                "(" +
                COLUNA_ID +
                " INTEGER PRIMARY KEY, " +
                COLUNA_TITULO +
                " VARCHAR NOT NULL, " +
                COLUNA_TEXTO +
                " VARCHAR NOT NULL)");

    }

    // Inserir uma nova nota no banco de dados
    public Nota inserirNota(Nota nota) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_TITULO, nota.getTitulo());
        values.put(COLUNA_TEXTO, nota.getTexto());
        database.insert(TABELA_NOTAS, null, values);
        return nota;
    }

    // Atualizar uma nota existente no banco de dados
    public Nota updateNota(Nota nota) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_TITULO, nota.getTitulo());
        values.put(COLUNA_TEXTO, nota.getTexto());

        String whereClause = COLUNA_ID + " = ?";
        String[] whereArgs = {String.valueOf(nota.getId())};

        database.update(TABELA_NOTAS, values, whereClause, whereArgs);
        return nota;
    }

    public void deleteNota(int id) {
        String idString = String.valueOf(id);
        database.delete(TABELA_NOTAS,
                COLUNA_ID + " = ?", new String[]{idString});
    }

    public Nota getNota(int notaId) {
        String query = "SELECT * FROM " + TABELA_NOTAS + " WHERE " + COLUNA_ID + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(notaId)});

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(+cursor.getColumnIndex(COLUNA_ID));
            String titulo = cursor.getString(+cursor.getColumnIndex(COLUNA_TITULO));
            String texto = cursor.getString(+cursor.getColumnIndex(COLUNA_TEXTO));

            Nota nota = new Nota(id, titulo, texto);
            return nota;
        }
        return null;
    }

    // Obter uma lista de todas as notas no banco de dados
    public ArrayList<Nota> getListNotas() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABELA_NOTAS + ";", null);
        cursor.moveToFirst();
        ArrayList<Nota> arrayList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(+cursor.getColumnIndex(COLUNA_ID));
            String texto = cursor.getString(+cursor.getColumnIndex(COLUNA_TEXTO));
            String titulo = cursor.getString(+cursor.getColumnIndex(COLUNA_TITULO));
            Nota nota = new Nota(id, titulo, texto);
            arrayList.add(nota);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
