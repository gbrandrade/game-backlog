package io.github.gbrandrade;

import io.github.gbrandrade.model.Plataforma;
import io.github.gbrandrade.repository.PlataformaRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        PlataformaRepository repository = new PlataformaRepository();

        Plataforma plataforma = repository.buscarPorId(1);
        System.out.println("Antes: " + plataforma);

        plataforma.setNome("PS5");
        repository.atualizar(plataforma);

        Plataforma atualizada = repository.buscarPorId(1);
        System.out.println("Depois: " + atualizada);
    }
}