package com.example.minhasnotas.model;

public class Nota {
    private int id;
    private String titulo;
    private String texto;

    public Nota(int id, String titulo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
    }
    public Nota(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}
