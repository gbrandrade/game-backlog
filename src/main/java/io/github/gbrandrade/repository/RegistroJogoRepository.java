package io.github.gbrandrade.repository;

import io.github.gbrandrade.config.ConexaoBD;
import io.github.gbrandrade.model.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroJogoRepository {

    private static final String SELECT_BASE =
            "SELECT r.id, r.status, r.nota, r.review, r.horas_pra_zerar, " +
                    "u.id AS usuario_id, u.nome AS usuario_nome, u.email AS usuario_email, u.senha AS usuario_senha, " +
                    "p.id AS plataforma_id, p.nome AS plataforma_nome, " +
                    "j.id AS jogo_id, j.nome AS jogo_nome, j.genero AS jogo_genero, j.ano_lancamento AS jogo_ano, " +
                    "e.id AS empresa_id, e.nome AS empresa_nome " +
                    "FROM registrojogo r " +
                    "JOIN usuario u ON r.usuario_id = u.id " +
                    "JOIN plataforma p ON r.plataforma_id = p.id " +
                    "JOIN jogo j ON r.jogo_id = j.id " +
                    "JOIN empresa e ON j.empresa_id = e.id";

    public void salvar(RegistroJogo registro) throws SQLException {
        String sql = "INSERT INTO registrojogo (usuario_id, jogo_id, plataforma_id, status, nota, review, horas_pra_zerar) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, registro.getUsuario().getId());
            stmt.setInt(2, registro.getJogo().getId());
            stmt.setInt(3, registro.getPlataforma().getId());
            stmt.setString(4, registro.getStatus().name());
            stmt.setDouble(5, registro.getNota());
            stmt.setString(6, registro.getReview());
            stmt.setInt(7, registro.getHorasParaZerar());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegistroJogo> listarTodos() throws SQLException {
        List<RegistroJogo> registros = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(SELECT_BASE);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                registros.add(montarRegistro(rs));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return registros;
    }

    public RegistroJogo buscarPorId(int id) throws SQLException {
        String sql = SELECT_BASE + " WHERE r.id = ?";
        RegistroJogo registro = null;

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    registro = montarRegistro(rs);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return registro;
    }

    public void atualizar(RegistroJogo registro) throws SQLException {
        String sql = "UPDATE registrojogo SET usuario_id = ?, jogo_id = ?, plataforma_id = ?, " +
                "status = ?, nota = ?, review = ?, horas_pra_zerar = ? WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, registro.getUsuario().getId());
            stmt.setInt(2, registro.getJogo().getId());
            stmt.setInt(3, registro.getPlataforma().getId());
            stmt.setString(4, registro.getStatus().name());
            stmt.setDouble(5, registro.getNota());
            stmt.setString(6, registro.getReview());
            stmt.setInt(7, registro.getHorasParaZerar());
            stmt.setInt(8, registro.getId());
            stmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private RegistroJogo montarRegistro(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario(
                rs.getInt("usuario_id"), rs.getString("usuario_nome"),
                rs.getString("usuario_email"), rs.getString("usuario_senha")
        );

        Plataforma plataforma = new Plataforma(rs.getInt("plataforma_id"), rs.getString("plataforma_nome"));

        Empresa empresa = new Empresa(rs.getInt("empresa_id"), rs.getString("empresa_nome"));

        Jogo jogo = new Jogo(
                rs.getInt("jogo_id"), rs.getString("jogo_nome"),
                Genero.valueOf(rs.getString("jogo_genero")), rs.getInt("jogo_ano"), empresa
        );

        return new RegistroJogo(
                rs.getInt("id"), usuario, jogo, plataforma,
                Status.valueOf(rs.getString("status")), rs.getDouble("nota"),
                rs.getString("review"), rs.getInt("horas_pra_zerar")
        );
    }
}