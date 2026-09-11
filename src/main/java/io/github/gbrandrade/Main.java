package io.github.gbrandrade;

import io.github.gbrandrade.model.Plataforma;
import io.github.gbrandrade.repository.PlataformaRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        PlataformaRepository repository = new PlataformaRepository();

        Plataforma plataforma = repository.buscarPorId(999);
        System.out.println(plataforma);
    }
}