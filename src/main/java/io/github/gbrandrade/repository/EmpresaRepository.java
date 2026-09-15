package io.github.gbrandrade.repository;

import io.github.gbrandrade.config.ConexaoBD;
import io.github.gbrandrade.model.Empresa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {

    public void salvar(Empresa empresa) throws SQLException {
        String sql = "INSERT INTO empresa (nome) VALUES (?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, empresa.getNome());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Empresa> listarTodas() throws SQLException {
        String sql = "SELECT * FROM empresa";
        List<Empresa> empresas = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Empresa empresa = new Empresa(id, nome);
                empresas.add(empresa);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return empresas;
    }

    public Empresa buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM empresa WHERE id = ?";
        Empresa empresa = null;

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empresa = new Empresa(id, rs.getString("nome"));
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return empresa;
    }

    public void atualizar(Empresa empresa) throws SQLException {
        String sql = "UPDATE empresa SET nome = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, empresa.getNome());
            stmt.setInt(2, empresa.getId());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}