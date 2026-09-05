package io.github.gbrandrade.repository;
import io.github.gbrandrade.config.ConexaoBD;
import io.github.gbrandrade.model.Plataforma;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlataformaRepository {

    public void salvar(Plataforma plataforma) throws SQLException {
        String sql = "INSERT INTO plataforma (nome) VALUES (?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, plataforma.getNome());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
