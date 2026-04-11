package br.com.repository;

import br.com.database.ConexaoBanco;
import br.com.model.entity.Usuario;
import br.com.model.enums.FuncaoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    // salvar os usuarios no banco de dados
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, cpf, funcao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getFuncao().name());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // atualizar as informacoes do usuario
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ?, funcao = ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getFuncao().name());
            stmt.setInt(4, usuario.getId());

            int linhas = stmt.executeUpdate();
            if (linhas == 0) System.out.println("Usuario não encontrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // vai listar os usuarios de dentro do banco
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        FuncaoUsuario.valueOf(rs.getString("funcao"))
                );
                usuario.setLivros_alugados_total(rs.getLong("livros_alugados_total"));
                usuario.setMulta_pendente(rs.getBoolean("multa_pendente"));
                usuario.setLivros_em_posse(rs.getInt("livros_em_posse"));
                usuario.setPossui_livro(rs.getBoolean("possui_livro"));
                usuario.setPontos(rs.getInt("pontos"));
                usuario.setAtivo(rs.getBoolean("ativo"));
                usuario.setValorMulta(rs.getInt("valorMulta"));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // vai deixar um usuario inativo
    public void inativarUsuario(int id) {
        String sql = "UPDATE usuarios SET ativo = false WHERE id = ? AND ativo = true";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();

            if (linhas == 0) System.out.println("Usuario não encontrado!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // vai adicionar os pontos e atualizar ele para o usuario
    public void adicionarPontos(int usuario_id, int pontosGanhos) {
        String sql = "UPDATE usuarios SET pontos = pontos + ? WHERE id = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pontosGanhos);
            stmt.setInt(2, usuario_id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // vai atualizar o status da multa
    public void atualizarStatusMulta(Usuario usuario) {
        String sql = "UPDATE usuarios SET multa_pendente = ?, livros_em_posse = ?, possui_livro = ?, valorMulta = ? WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, usuario.isMulta_pendente());
            stmt.setInt(2, usuario.getLivros_em_posse());
            stmt.setBoolean(3, usuario.isPossui_livro());
            stmt.setInt(4, usuario.getValorMulta());
            stmt.setInt(5, usuario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do usuário no banco", e);
        }
    }
}