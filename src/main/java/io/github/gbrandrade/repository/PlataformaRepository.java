package io.github.gbrandrade.repository;

import io.github.gbrandrade.config.ConexaoBD;
import io.github.gbrandrade.model.Plataforma;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

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

    public List<Plataforma> listarTodas() throws SQLException {
        String sql = "SELECT * FROM plataforma";
        List<Plataforma> plataformas = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Plataforma plataforma = new Plataforma(id, nome);
                plataformas.add(plataforma);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return plataformas;
    }

    public Plataforma buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM plataforma WHERE id = ?";
        Plataforma plataforma = null;

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    plataforma = new Plataforma(id, rs.getString("nome"));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return plataforma;
    }

    public void atualizar(Plataforma plataforma) throws SQLException {
        String sql = "UPDATE plataforma SET nome = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, plataforma.getNome());
            stmt.setInt(2, plataforma.getId());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}