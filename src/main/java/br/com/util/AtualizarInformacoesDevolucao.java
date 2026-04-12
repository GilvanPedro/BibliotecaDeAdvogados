package br.com.util;

import br.com.model.entity.HistoricoLeitura;
import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.service.HistoricoLeituraService;

public class AtualizarInformacoesDevolucao {

    private final HistoricoLeituraService historicoService = new HistoricoLeituraService();

    public void atualizar(HistoricoLeitura historico) {
        Usuario u = historico.getUsuario();
        Livro l = historico.getLivro();

        // atualiza estoque e posse
        u.setLivros_em_posse(u.getLivros_em_posse() - 1);
        l.setQuantidadeLivros(l.getQuantidadeLivros() + 1);
        historico.getEmprestimo().setLivro_devolvido(true);

        if (u.getLivros_em_posse() <= 0) {
            u.setPossui_livro(false);
            u.setLivros_em_posse(0);
        }

        // atualiza o status no histórico
        historicoService.atualizarStatusParaDevolvido(historico);
    }
}