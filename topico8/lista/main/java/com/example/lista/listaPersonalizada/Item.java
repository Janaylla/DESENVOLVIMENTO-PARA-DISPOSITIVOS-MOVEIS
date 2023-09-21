package com.example.lista.listaPersonalizada;

public class Item {
    public Item(Integer id, String nome, int foto) {
        this.id = id;
        this.nome = nome;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getFoto() {
        return foto;
    }

    private Integer id;
    private String nome;
    private int foto;
}
