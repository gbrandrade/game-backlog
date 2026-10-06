package io.github.gbrandrade;

import io.github.gbrandrade.service.RawgApiClient;
import io.github.gbrandrade.dto.JogoRawgDTO;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RawgApiClient api = new RawgApiClient();

        try {
            System.out.println("Buscando 'Grand Theft Auto VI'...\n");
            List<JogoRawgDTO> resultados = api.buscarJogosPorNome("Grand Theft Auto VI");

            for (JogoRawgDTO jogo : resultados) {
                System.out.println("ID: " + jogo.getId());
                System.out.println("Nome: " + jogo.getName());
                System.out.println("Lançamento: " + jogo.getDataLancamento());
                System.out.println("Capa: " + jogo.getImagemCapa());
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}