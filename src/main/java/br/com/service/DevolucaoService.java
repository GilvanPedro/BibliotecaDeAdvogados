package br.com.service;

import br.com.model.entity.HistoricoLeitura;
import br.com.model.entity.Usuario;
import br.com.util.AtualizarInformacoesDevolucao;
import br.com.util.Multas;
import br.com.util.ValidarDevolucaoLivro;

public class DevolucaoService {

    private final ValidarDevolucaoLivro validadorDevolucao = new ValidarDevolucaoLivro();
    Multas multas = new Multas();
    private final DevolucaoService devolucaoService = new DevolucaoService();
    AtualizarInformacoesDevolucao atualizarDevolucao = new AtualizarInformacoesDevolucao();
    UsuarioService usuarioService = new UsuarioService();
    HistoricoLeituraService historicoLeituraService = new HistoricoLeituraService();

    private final int devolvidoEmDiaPonto = 2;

    // Devolução de livro
    public String devolverLivro(HistoricoLeitura historicoLeitura) {
        // verificar se teve atraso na devolucao
        boolean emDia = validadorDevolucao.devolvidoEmDia(historicoLeitura.getEmprestimo());
        Usuario usuario = historicoLeitura.getUsuario();

        atualizarDevolucao.atualizar(historicoLeituraService.buscarHistoricoPorEmprestimoId(historicoLeitura.getEmprestimo().getId()));

        // lógica de Multa e Pontuação
        if (!emDia) {
            int multaValor = multas.calcularMulta(historicoLeitura.getEmprestimo());
            usuarioService.adicionarMulta(historicoLeitura.getUsuario().getId(), multaValor);
        } else {
            usuarioService.adicionarPontos(usuario, devolvidoEmDiaPonto);
        }

        // PERSISTÊNCIA
        devolucaoService.devolverLivro(historicoLeitura);
        historicoLeituraService.atualizarStatusParaDevolvido(historicoLeitura);

        return emDia ? "Livro devolvido com sucesso!"
                : "Livro devolvido com atraso. Multa: R$ " + usuario.getValorMulta();
    }
}
