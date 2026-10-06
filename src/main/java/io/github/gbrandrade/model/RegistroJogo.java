package io.github.gbrandrade.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registros_jogos")
public class RegistroJogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relação: Muitos Registos pertencem a Um Utilizador
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // O ID que vem da API do RAWG (ex: 3498 para GTA V)
    @Column(name = "id_jogo_rawg", nullable = false)
    private Long idJogoRawg;

    @Column(nullable = false)
    private Integer nota; // De 1 a 5

    @Column(nullable = false, length = 20)
    private String status; // JOGANDO, FINALIZADO, BACKLOG, DROPADO

    @Column(columnDefinition = "TEXT")
    private String review;

    @Column(name = "data_registro", nullable = false)
    private LocalDateTime dataRegistro = LocalDateTime.now();

    public RegistroJogo() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Long getIdJogoRawg() { return idJogoRawg; }
    public void setIdJogoRawg(Long idJogoRawg) { this.idJogoRawg = idJogoRawg; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }
}