package br.com.repository;

import br.com.database.ConexaoBanco;
import br.com.model.dto.HistoricoDTO;
import br.com.model.entity.Emprestimo;
import br.com.model.entity.HistoricoLeitura;
import br.com.model.enums.StatusLeitura;
import br.com.model.enums.TipoLeitura;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistoricoLeituraRepository {

    public void salvar(HistoricoLeitura historico) {
        String sql = "INSERT INTO historico_leitura (usuario_id, livro_id, emprestimo_id, tipo_leitura, status_leitura) VALUES (?,?,?,?,?)";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, historico.getUsuario().getId());
            stmt.setInt(2, historico.getLivro().getId());
            stmt.setInt(3, historico.getEmprestimo().getId());
            stmt.setString(4, historico.getTipoLeitura().name());
            stmt.setString(5, historico.getStatusLeitura().name());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<HistoricoDTO> listar() {
        List<HistoricoDTO> historicos = new ArrayList<>();
        String sql = """
            SELECT hl.id, e.emprestimo_data, e.devolucao_data, e.livro_devolvido,
                   u.nome AS usuario_nome, u.cpf AS usuario_cpf, u.funcao AS usuario_funcao,
                   l.titulo AS livro_titulo, l.autor AS livro_autor, l.categoria AS livro_categoria,
                   hl.tipo_leitura, hl.status_leitura
            FROM historico_leitura hl
            JOIN usuarios u ON hl.usuario_id = u.id
            JOIN livros l ON hl.livro_id = l.id
            JOIN emprestimo e ON hl.emprestimo_id = e.id
        """;

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HistoricoDTO dto = new HistoricoDTO(
                        rs.getInt("id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_cpf"),
                        rs.getString("usuario_funcao"),
                        rs.getString("livro_titulo"),
                        rs.getString("livro_autor"),
                        rs.getString("livro_categoria"),
                        rs.getDate("emprestimo_data").toLocalDate(),
                        rs.getDate("devolucao_data").toLocalDate(),
                        rs.getBoolean("livro_devolvido"),
                        TipoLeitura.valueOf(rs.getString("tipo_leitura")),
                        StatusLeitura.valueOf(rs.getString("status_leitura"))
                );
                historicos.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return historicos;
    }

    public HistoricoLeitura buscarPorEmprestimoId(int emprestimoId) {
        String sql = "SELECT * FROM historico_leitura WHERE emprestimo_id = ?";
        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Aqui retornamos o objeto básico para atualização
                return new HistoricoLeitura(
                        rs.getInt("id"),
                        null, // usuario pode ser nulo se você só for usar o ID para o UPDATE
                        null, // Livro
                        null, // Emprestimo
                        null, // TipoLeitura
                        StatusLeitura.valueOf(rs.getString("status_leitura"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Emprestimo emprestimo, StatusLeitura status) {
        String sql = "UPDATE historico_leitura SET status_leitura = ? WHERE id = ?";

        HistoricoLeitura historicoLeitura = buscarPorEmprestimoId(emprestimo.getId());

        if (historicoLeitura == null) {
            System.out.println("Histórico não encontrado para esse empréstimo!");
            return;
        }

        try (Connection conn = ConexaoBanco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, historicoLeitura.getId());

            int linhas = stmt.executeUpdate();
            if (linhas == 0) System.out.println("Usuario não encontrado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}