package io.github.gbrandrade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    // Funcionalidades Premium (Cosméticas)
    @Column(name = "is_premium", nullable = false)
    private boolean isPremium = false;

    @Column(name = "cor_borda_perfil", length = 7)
    private String corBordaPerfil; // Ex: "#0055ff" (estilo Google One)

    @Column(name = "url_banner_perfil")
    private String urlBannerPerfil;

    // Construtor vazio obrigatório para o JPA
    public Usuario() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean isPremium() { return isPremium; }
    public void setPremium(boolean premium) { this.isPremium = premium; }

    public String getCorBordaPerfil() { return corBordaPerfil; }
    public void setCorBordaPerfil(String corBordaPerfil) { this.corBordaPerfil = corBordaPerfil; }

    public String getUrlBannerPerfil() { return urlBannerPerfil; }
    public void setUrlBannerPerfil(String urlBannerPerfil) { this.urlBannerPerfil = urlBannerPerfil; }
}