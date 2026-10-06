package io.github.gbrandrade.repository;

import io.github.gbrandrade.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // O Spring gera o SQL disto automaticamente só pelo nome do método
    Usuario findByUsername(String username);
}