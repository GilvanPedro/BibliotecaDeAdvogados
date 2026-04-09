package br.com.repository;

import br.com.database.ConexaoBanco;
import br.com.model.entity.Livro;
import br.com.model.enums.CategoriaLivros;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    // criar um livro no banco de dados
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, editora, categoria, quantidade_livros) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getCategoria().name());
            stmt.setInt(5, livro.getQuantidadeLivros());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // visualizar os livros no banco de dados
    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        CategoriaLivros.valueOf(rs.getString("categoria")),
                        rs.getInt("quantidade_livros")
                );

                livros.add(livro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return livros;
    }

    // editar as informações de um livro
    public void atualizar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, categoria = ? WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getCategoria().name());
            stmt.setInt(5, livro.getId());

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                System.out.println("Livro não encontrado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // inativar um livro do banco de dados
    public void inativarLivro(int id) {
        String sql = "UPDATE livros SET ativo = false WHERE id = ?";

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                System.out.println("Livro não encontrado!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}