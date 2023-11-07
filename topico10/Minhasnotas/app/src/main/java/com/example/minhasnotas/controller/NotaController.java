package com.example.minhasnotas.controller;

import android.content.Context;

import com.example.minhasnotas.model.Nota;
import com.example.minhasnotas.model.NotaDAO;

import java.util.ArrayList;

public class NotaController {

    Context context;
    NotaDAO notaDAO;

    public NotaController(Context context) {
        this.context = context;
        notaDAO = new NotaDAO(context);
    }

    public Nota cadastrarNovaNota(Nota nota) {
        return notaDAO.inserirNota(nota);
    }

    public Nota atualizarNota(Nota nota) {
        return notaDAO.updateNota(nota);
    }

    public void excluirNota(int id) {
        notaDAO.deleteNota(id);
    }

    public Nota getNota(int notaId) {
        return notaDAO.getNota(notaId);
    }

    public ArrayList<Nota> getListNotas() {
        return notaDAO.getListNotas();
    }
}
