package br.com.repository;

import br.com.database.ConexaoBanco;
import br.com.model.entity.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DevolucaoRepository {

    // Devolver livro
    public void devolverLivro(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET livro_devolvido = true WHERE id = ? AND livro_devolvido = false";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getId());
            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                System.out.println("Empréstimo já devolvido ou não encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
