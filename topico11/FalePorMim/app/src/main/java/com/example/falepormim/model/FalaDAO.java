package com.example.falepormim.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FalaDAO {

    private SQLiteDatabase database;
    private static final String COLUNA_TEXTO = "texto";
    private static final String COLUNA_ID = "id";
    private static final String TABELA_NOTAS = "falas";
    private static final String DB_NOTAS = "falas";

    public FalaDAO(Context context) {
        database = context.openOrCreateDatabase(DB_NOTAS, context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABELA_NOTAS +
                "(" +
                COLUNA_ID +
                " INTEGER PRIMARY KEY, " +
                COLUNA_TEXTO +
                " VARCHAR NOT NULL)");

    }

    // Inserir uma nova fala no banco de dados
    public Fala inserirFala(Fala fala) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_TEXTO, fala.getTexto());
        database.insert(TABELA_NOTAS, null, values);
        return fala;
    }

    // Atualizar uma fala existente no banco de dados
    public Fala updateFala(Fala fala) {
        ContentValues values = new ContentValues();
        values.put(COLUNA_TEXTO, fala.getTexto());

        String whereClause = COLUNA_ID + " = ?";
        String[] whereArgs = {String.valueOf(fala.getId())};

        database.update(TABELA_NOTAS, values, whereClause, whereArgs);
        return fala;
    }

    public void deleteFala(int id) {
        String idString = String.valueOf(id);
        database.delete(TABELA_NOTAS,
                COLUNA_ID + " = ?", new String[]{idString});
    }
    public void deleteAll() {
        database.delete(TABELA_NOTAS, "",  new String[]{});
    }

    public Fala getFala(int falaId) {
        String query = "SELECT * FROM " + TABELA_NOTAS + " WHERE " + COLUNA_ID + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(falaId)});

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(+cursor.getColumnIndex(COLUNA_ID));
            String texto = cursor.getString(+cursor.getColumnIndex(COLUNA_TEXTO));

            Fala fala = new Fala(id, texto);
            return fala;
        }
        return null;
    }

    // Obter uma lista de todas as falas no banco de dados
    public ArrayList<Fala> getListFalas() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABELA_NOTAS + ";", null);
        cursor.moveToFirst();
        ArrayList<Fala> arrayList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(+cursor.getColumnIndex(COLUNA_ID));
            String texto = cursor.getString(+cursor.getColumnIndex(COLUNA_TEXTO));
            Fala fala = new Fala(id, texto);
            arrayList.add(fala);
            cursor.moveToNext();
        }
        return arrayList;
    }
}
