package io.github.gbrandrade.model;

public class Empresa {

    private int id;
    private String nome;

    public Empresa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Empresa(String nome) {
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
        return "Empresa{id=" + id + ", nome='" + nome + "'}";
    }
}