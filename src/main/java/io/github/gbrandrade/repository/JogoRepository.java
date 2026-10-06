package io.github.gbrandrade.repository;

import io.github.gbrandrade.config.ConexaoBD;
import io.github.gbrandrade.model.Empresa;
import io.github.gbrandrade.model.Genero;
import io.github.gbrandrade.model.Jogo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogoRepository {

    private static final String SELECT_COM_JOIN =
            "SELECT jogo.id, jogo.nome, jogo.genero, jogo.ano_lancamento, " +
                    "empresa.id AS empresa_id, empresa.nome AS empresa_nome " +
                    "FROM jogo JOIN empresa ON jogo.empresa_id = empresa.id";

    public void salvar(Jogo jogo) throws SQLException {
        String sql = "INSERT INTO jogo (nome, genero, ano_lancamento, empresa_id) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getGenero().name());
            stmt.setInt(3, jogo.getAnoLancamento());
            stmt.setInt(4, jogo.getEmpresa().getId());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Jogo> listarTodos() throws SQLException {
        List<Jogo> jogos = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(SELECT_COM_JOIN);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                jogos.add(montarJogo(rs));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jogos;
    }

    public Jogo buscarPorId(int id) throws SQLException {
        String sql = SELECT_COM_JOIN + " WHERE jogo.id = ?";
        Jogo jogo = null;

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    jogo = montarJogo(rs);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jogo;
    }

    public void atualizar(Jogo jogo) throws SQLException {
        String sql = "UPDATE jogo SET nome = ?, genero = ?, ano_lancamento = ?, empresa_id = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, jogo.getNome());
            stmt.setString(2, jogo.getGenero().name());
            stmt.setInt(3, jogo.getAnoLancamento());
            stmt.setInt(4, jogo.getEmpresa().getId());
            stmt.setInt(5, jogo.getId());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Jogo montarJogo(ResultSet rs) throws SQLException {
        Empresa empresa = new Empresa(rs.getInt("empresa_id"), rs.getString("empresa_nome"));

        return new Jogo(
                rs.getInt("id"),
                rs.getString("nome"),
                Genero.valueOf(rs.getString("genero")),
                rs.getInt("ano_lancamento"),
                empresa
        );
    }
}