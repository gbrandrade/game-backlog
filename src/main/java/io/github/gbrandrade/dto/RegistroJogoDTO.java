package io.github.gbrandrade.dto;

public class RegistroJogoDTO {

    private Long usuarioId;
    private Long idJogoRawg;
    private Integer nota;
    private String status;
    private String review;

    // Getters e Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getIdJogoRawg() { return idJogoRawg; }
    public void setIdJogoRawg(Long idJogoRawg) { this.idJogoRawg = idJogoRawg; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}