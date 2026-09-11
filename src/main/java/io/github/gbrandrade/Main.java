package io.github.gbrandrade;

import io.github.gbrandrade.model.Plataforma;
import io.github.gbrandrade.repository.PlataformaRepository;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        PlataformaRepository repository = new PlataformaRepository();

        List<Plataforma> plataformas = repository.listarTodas();

        for (Plataforma p : plataformas) {
            System.out.println(p);
        }
    }
}