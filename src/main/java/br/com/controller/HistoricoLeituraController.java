package br.com.controller;

import br.com.model.dto.HistoricoDTO;
import br.com.model.entity.Emprestimo;
import br.com.model.entity.HistoricoLeitura;
import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.model.enums.StatusLeitura;
import br.com.model.enums.TipoLeitura;
import br.com.service.HistoricoLeituraService;

import java.util.Comparator;
import java.util.List;

public class HistoricoLeituraController {

    HistoricoLeituraService historicoLeituraService = new HistoricoLeituraService();
    UsuarioController usuarioController = new UsuarioController();
    LivroController livroController = new LivroController();
    EmprestimoController emprestimoController = new EmprestimoController();

    // buscar pelo historico
    public HistoricoLeitura buscarHistorico(int id){
        List<HistoricoLeitura> listaHistoricos = historicoLeituraService.listarHistoricoEntity();

        // ordenar a lista pelo id
        listaHistoricos.sort(Comparator.comparingInt(HistoricoLeitura::getId));

        int left = 0;
        int right = listaHistoricos.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            HistoricoLeitura historico = listaHistoricos.get(mid);

            if (historico.getId() == id) {
                // encontrou o historico
                return historico;

            } else if (historico.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // nao encontrou o historico
        return null;
    }

    // salvar o historico
    public void salvarHistorico(int id_usuario, int id_livro, int id_emprestimo, TipoLeitura tipoLeitura, StatusLeitura statusLeitura){

        Emprestimo emprestimo = emprestimoController.buscarEmprestimo(id_emprestimo);
        Livro livro = livroController.buscarLivro(id_livro);
        Usuario usuario = usuarioController.buscarUsuario(id_usuario);

        HistoricoLeitura novoHistorico = new HistoricoLeitura(
                usuario,
                livro,
                emprestimo,
                tipoLeitura,
                statusLeitura
        );

        historicoLeituraService.salvarHistorico(novoHistorico);
    }

    // atualizar o status para devolvido
    public void atualizarStatusParaDevolvido(int id){
        HistoricoLeitura historico = buscarHistorico(id);

        historicoLeituraService.atualizarStatusParaDevolvido(historico);

    }

    // listar tabela historico de leitura
    public List<HistoricoDTO> tabelaHistoricoLeitura(){
        return historicoLeituraService.listarHistoricoDTO();
    }
}
