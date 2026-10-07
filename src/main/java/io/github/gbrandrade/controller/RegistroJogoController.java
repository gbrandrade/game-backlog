package io.github.gbrandrade.controller;

import io.github.gbrandrade.dto.RegistroJogoDTO;
import io.github.gbrandrade.model.RegistroJogo;
import io.github.gbrandrade.model.Usuario;
import io.github.gbrandrade.repository.RegistroJogoRepository;
import io.github.gbrandrade.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/registros")
public class RegistroJogoController {

    @Autowired
    private RegistroJogoRepository registroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarRegistro(@RequestBody RegistroJogoDTO dto) {
        // 1. Verifica se o utilizador existe na base de dados
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getUsuarioId());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Utilizador não encontrado.");
        }

        // 2. Transforma o DTO na Entidade que será guardada na base de dados
        RegistroJogo registro = new RegistroJogo();
        registro.setUsuario(usuarioOpt.get());
        registro.setIdJogoRawg(dto.getIdJogoRawg());
        registro.setNota(dto.getNota());
        registro.setStatus(dto.getStatus());
        registro.setReview(dto.getReview());

        // 3. Guarda no PostgreSQL
        registroRepository.save(registro);

        return ResponseEntity.ok("Review guardada com sucesso!");
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<RegistroJogo>> buscarRegistrosDoUsuario(@PathVariable Long usuarioId) {
        // Busca todos os registros vinculados ao ID daquele usuário
        List<RegistroJogo> registros = registroRepository.findByUsuarioId(usuarioId);

        if (registros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(registros);
    }
}