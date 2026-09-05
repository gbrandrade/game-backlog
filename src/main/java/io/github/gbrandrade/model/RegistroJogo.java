package io.github.gbrandrade.model;

public class RegistroJogo {

    private int id;
    private Usuario usuario;
    private Jogo jogo;
    private Plataforma plataforma;
    private Status status;
    private double nota;
    private String review;
    private int horasParaZerar;

    public RegistroJogo(int id, Usuario usuario, Jogo jogo, Plataforma plataforma, Status status, double nota, String review, int horasParaZerar) {
        this.id = id;
        this.usuario = usuario;
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.status = status;
        this.nota = nota;
        this.review = review;
        this.horasParaZerar = horasParaZerar;
    }

    public RegistroJogo(Usuario usuario, Jogo jogo, Plataforma plataforma, Status status, double nota, String review, int horasParaZerar) {
        this.usuario = usuario;
        this.jogo = jogo;
        this.plataforma = plataforma;
        this.status = status;
        this.nota = nota;
        this.review = review;
        this.horasParaZerar = horasParaZerar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setHorasParaZerar(int horasParaZerar) {
        this.horasParaZerar = horasParaZerar;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public Status getStatus() {
        return status;
    }

    public double getNota() {
        return nota;
    }

    public String getReview() {
        return review;
    }

    public int getHorasParaZerar() {
        return horasParaZerar;
    }

    @Override
    public String toString() {
        return "RegistroJogo{id=" + id + ", usuario='" + usuario.getNome() + "', jogo='" + jogo.getNome() + "', plataforma='" + plataforma.getNome() + "', status=" + status + ", nota=" + nota + ", review='" + review + "', horasParaZerar=" + horasParaZerar + "}";
    }

}