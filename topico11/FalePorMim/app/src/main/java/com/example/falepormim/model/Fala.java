package com.example.falepormim.model;

public class Fala {
    private int id;
    private String texto;

    public Fala(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }
    public Fala(String texto) {
        this.texto = texto;
    }

    public int getId() {
        return this.id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Fala{" +
                "id=" + id + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}
