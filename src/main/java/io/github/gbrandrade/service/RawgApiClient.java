package io.github.gbrandrade.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.gbrandrade.dto.RespostaBuscaRawgDTO;
import io.github.gbrandrade.dto.JogoRawgDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RawgApiClient {

    private static final String API_KEY = "e974ce24d94c4c138f97d34fc12357c6";
    private static final String BASE_URL = "https://api.rawg.io/api/games";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public RawgApiClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<JogoRawgDTO> buscarJogosPorNome(String nomeJogo) throws Exception {
        String queryBusca = nomeJogo.replace(" ", "%20");
        String urlCompleta = BASE_URL + "?key=" + API_KEY + "&search=" + queryBusca + "&page_size=5";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            RespostaBuscaRawgDTO resposta = objectMapper.readValue(response.body(), RespostaBuscaRawgDTO.class);
            return resposta.getResults();
        } else {
            throw new RuntimeException("Erro na API: Código " + response.statusCode());
        }
    }
}