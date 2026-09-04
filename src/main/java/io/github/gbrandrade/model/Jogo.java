package io.github.gbrandrade.model;

public class Jogo {

    private int id;
    private String nome;
    private Genero genero;
    private int anoLancamento;
    private Empresa empresa;

    public Jogo(int id, String nome, Genero genero, int anoLancamento, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.empresa = empresa;
    }

    public Jogo(String nome, Genero genero, int anoLancamento, Empresa empresa) {
        this.nome = nome;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
        this.empresa = empresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    @Override
    public String toString() {
        return "Jogo{id=" + id + ", nome='" + nome + "', genero=" + genero + ", anoLancamento=" + anoLancamento + ", empresa='" + empresa.getNome() + "'}";
    }

}