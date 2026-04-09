package br.com.repository;

import br.com.database.ConexaoBanco;
import br.com.model.dto.EmprestimoDTO;
import br.com.model.entity.Emprestimo;
import br.com.model.entity.HistoricoLeitura;
import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.model.enums.StatusLeitura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    // Adicionar empréstimo
    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (usuario_id, livro_id, emprestimo_data, devolucao_data, livro_devolvido) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setInt(2, emprestimo.getLivro().getId());
            stmt.setDate(3, java.sql.Date.valueOf(emprestimo.getEmprestimo_data()));
            stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDevolucao_data()));
            stmt.setBoolean(5, emprestimo.isLivro_devolvido());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Listar empréstimos
    public List<EmprestimoDTO> listar() {
        List<EmprestimoDTO> emprestimos = new ArrayList<>();
        String sql = """
            SELECT e.id, e.emprestimo_data, e.devolucao_data, e.livro_devolvido,
                   u.nome, u.cpf, u.funcao,
                   l.titulo, l.autor, l.categoria
            FROM emprestimo e
            JOIN usuarios u ON e.usuario_id = u.id
            JOIN livros l ON e.livro_id = l.id
        """;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmprestimoDTO dto = new EmprestimoDTO(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("funcao"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("categoria"),
                        rs.getDate("emprestimo_data").toLocalDate(),
                        rs.getDate("devolucao_data").toLocalDate(),
                        rs.getBoolean("livro_devolvido")
                );

                emprestimos.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    public EmprestimoDTO buscarPorEmprestimo(int idEmprestimo){

    }

}