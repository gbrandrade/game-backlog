package io.github.gbrandrade.model;

public class Plataforma {

    private int id;
    private String nome;

    public Plataforma(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Plataforma(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Plataforma{id=" + id + ", nome='" + nome + "'}";
    }

}