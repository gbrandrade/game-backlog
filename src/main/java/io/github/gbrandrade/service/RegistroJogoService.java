package io.github.gbrandrade.service;

import io.github.gbrandrade.model.RegistroJogo;
import io.github.gbrandrade.model.Status;
import io.github.gbrandrade.repository.RegistroJogoRepository;

import java.sql.SQLException;

public class RegistroJogoService {

    private final RegistroJogoRepository repository = new RegistroJogoRepository();

    public void salvar(RegistroJogo registro) throws SQLException {
        validarCamposObrigatorios(registro);

        if (registro.getStatus() != Status.QUERO_JOGAR) {
            avisarSePreenchimentoIncompleto(registro);
        }

        repository.salvar(registro);
    }

    private void validarCamposObrigatorios(RegistroJogo registro) {
        if (registro.getJogo() == null) {
            throw new IllegalArgumentException("O jogo é obrigatório.");
        }
        if (registro.getStatus() == null) {
            throw new IllegalArgumentException("O status é obrigatório.");
        }
    }

    private void avisarSePreenchimentoIncompleto(RegistroJogo registro) {
        if (registro.getReview() == null || registro.getReview().isBlank()) {
            System.out.println("Aviso: você marcou o status como " + registro.getStatus()
                    + ", mas não escreveu uma resenha.");
        }
        if (registro.getNota() == 0.0) {
            System.out.println("Aviso: você marcou o status como " + registro.getStatus()
                    + ", mas não deu uma nota.");
        }
        if (registro.getHorasParaZerar() == 0) {
            System.out.println("Aviso: você marcou o status como " + registro.getStatus()
                    + ", mas não informou as horas para zerar.");
        }
    }
}