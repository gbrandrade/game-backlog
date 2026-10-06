package io.github.gbrandrade.controller;

import io.github.gbrandrade.dto.JogoRawgDTO;
import io.github.gbrandrade.service.RawgApiClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final RawgApiClient rawgApiClient;

    public JogoController() {
        // Instanciamos o cliente que você criou no passo anterior
        this.rawgApiClient = new RawgApiClient();
    }

    // Este método "escuta" a URL e pega o que o usuário digitou na busca
    @GetMapping("/buscar")
    public List<JogoRawgDTO> buscarJogos(@RequestParam String nome) {
        try {
            return rawgApiClient.buscarJogosPorNome(nome);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Em um cenário ideal, retornaríamos um erro HTTP adequado
        }
    }
}