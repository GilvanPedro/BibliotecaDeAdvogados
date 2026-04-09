package br.com.util;

import br.com.model.entity.Emprestimo;
import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.repository.HistoricoLeituraRepository;
import br.com.repository.UsuarioRepository;
import br.com.service.HistoricoLeituraService;

public class AtualizarInformacoesDevolucao {

    private final HistoricoLeituraService historicoService = new HistoricoLeituraService();

    public void atualizar(Emprestimo emprestimo) {
        Usuario u = emprestimo.getUsuario();
        Livro l = emprestimo.getLivro();

        // atualiza estoque e posse
        u.setLivros_em_posse(u.getLivros_em_posse() - 1);
        l.setQuantidadeLivros(l.getQuantidadeLivros() + 1);
        emprestimo.setLivro_devolvido(true);

        if (u.getLivros_em_posse() <= 0) {
            u.setPossui_livro(false);
            u.setLivros_em_posse(0);
        }

        // atualiza o status no histórico
        historicoService.atualizarStatusParaDevolvido(emprestimo);
    }
}