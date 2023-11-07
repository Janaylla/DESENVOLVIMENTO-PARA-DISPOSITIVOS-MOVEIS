package com.example.falepormim.controller;

import android.content.Context;

import com.example.falepormim.model.Fala;
import com.example.falepormim.model.FalaDAO;

import java.util.ArrayList;

public class FalaController {


    Context context;
    FalaDAO falaDAO;

    public FalaController(Context context) {
        this.context = context;
        falaDAO = new FalaDAO(context);
    }

    public Fala cadastrarNovaFala(Fala fala) {
        return falaDAO.inserirFala(fala);
    }

    public Fala atualizarFala(Fala fala) {
        return falaDAO.updateFala(fala);
    }

    public void excluirFala(int id) {
        falaDAO.deleteFala(id);
    }
    public void excluirTudo() {
        falaDAO.deleteAll();
    }

    public Fala getFala(int falaId) {
        return falaDAO.getFala(falaId);
    }

    public ArrayList<Fala> getListFalas() {
        return falaDAO.getListFalas();
    }
}
