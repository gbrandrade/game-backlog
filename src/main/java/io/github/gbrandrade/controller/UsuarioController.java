package io.github.gbrandrade.controller;

import io.github.gbrandrade.model.Usuario;
import io.github.gbrandrade.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        // Num cenário real de produção, a password deve ser encriptada aqui (ex: com BCrypt) antes de salvar.
        // Para o MVP, vamos guardar diretamente.
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(novoUsuario);
    }
}