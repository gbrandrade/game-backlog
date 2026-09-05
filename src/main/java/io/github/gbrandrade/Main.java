package io.github.gbrandrade;
import io.github.gbrandrade.config.ConexaoBD;
import java.sql.Connection;
import io.github.gbrandrade.model.Plataforma;
import io.github.gbrandrade.repository.PlataformaRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        Plataforma plataforma = new Plataforma("PlayStation 5");

        PlataformaRepository repository = new PlataformaRepository();
        repository.salvar(plataforma);

        System.out.println("Plataforma salva com sucesso!");
    }
}
