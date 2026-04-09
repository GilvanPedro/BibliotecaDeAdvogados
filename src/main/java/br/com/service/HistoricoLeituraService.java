package br.com.service;

import br.com.model.entity.Emprestimo;
import br.com.model.entity.HistoricoLeitura;
import br.com.model.enums.StatusLeitura;
import br.com.repository.HistoricoLeituraRepository;

public class HistoricoLeituraService {

    HistoricoLeituraRepository historicoRepository = new HistoricoLeituraRepository();

    public String salvarHistorico(HistoricoLeitura historico){
        historicoRepository.salvar(historico);

        return "Historico salvo com sucesso";
    }

    // atualiza o livro para devolvido
    public String atualizarStatusParaDevolvido(Emprestimo emprestimo) {
        // Busca o registro vinculado ao empréstimo
        HistoricoLeitura historico = historicoRepository.buscarPorEmprestimoId(emprestimo.getId());

        if (historico != null) {
            // Altera o status na memória
            historico.setStatusLeitura(StatusLeitura.DEVOLVIDO);
            // Manda o repository salvar no banco
            historicoRepository.atualizar(emprestimo, StatusLeitura.DEVOLVIDO);

            return "Status alterado com sucesso";
        }

        return "Falha ao alterar o status de leitura";
    }
}
