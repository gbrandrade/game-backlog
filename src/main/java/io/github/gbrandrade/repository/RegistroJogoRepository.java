package io.github.gbrandrade.repository;

import io.github.gbrandrade.model.RegistroJogo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistroJogoRepository extends JpaRepository<RegistroJogo, Long> {
    // Encontra todos os jogos registados por um utilizador específico (para a página de perfil)
    List<RegistroJogo> findByUsuarioId(Long usuarioId);
}