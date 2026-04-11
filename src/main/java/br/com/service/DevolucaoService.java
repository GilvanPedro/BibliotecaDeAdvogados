package br.com.service;

import br.com.model.entity.Emprestimo;
import br.com.model.entity.Usuario;
import br.com.model.enums.StatusLeitura;
import br.com.repository.DevolucaoRepository;
import br.com.repository.HistoricoLeituraRepository;
import br.com.repository.UsuarioRepository;
import br.com.util.AtualizarInformacoesDevolucao;
import br.com.util.Multas;
import br.com.util.ValidarDevolucaoLivro;

public class DevolucaoService {

    private final ValidarDevolucaoLivro validadorDevolucao = new ValidarDevolucaoLivro();
    Multas multas = new Multas();
    private final DevolucaoRepository devolucaoRepository = new DevolucaoRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    AtualizarInformacoesDevolucao atualizarDevolucao = new AtualizarInformacoesDevolucao();
    private final HistoricoLeituraRepository historicoLeituraRepository = new HistoricoLeituraRepository();
    UsuarioService usuarioService = new UsuarioService();
    HistoricoLeituraService historicoLeituraService = new HistoricoLeituraService();

    private final int devolvidoEmDiaPonto = 2;

    // Devolução de livro
    public String devolverLivro(Emprestimo emprestimo) {
        // verificar se teve atraso na devolucao
        boolean emDia = validadorDevolucao.devolvidoEmDia(emprestimo);
        Usuario usuario = emprestimo.getUsuario();

        atualizarDevolucao.atualizar(historicoLeituraService.buscarHistoricoPorEmprestimoId(emprestimo.getId()));

        // lógica de Multa e Pontuação
        if (!emDia) {
            int multaValor = multas.calcularMulta(emprestimo);
            usuario.setValorMulta(multaValor);
            usuario.setMulta_pendente(true); // Garante que o status mude
        } else {
            usuarioService.adicionarPontos(usuario, devolvidoEmDiaPonto);
        }

        // PERSISTÊNCIA
        devolucaoRepository.devolverLivro(emprestimo);
        usuarioRepository.atualizarStatusMulta(usuario);
        historicoLeituraRepository.atualizar(emprestimo, StatusLeitura.DEVOLVIDO);

        return emDia ? "Livro devolvido com sucesso!"
                : "Livro devolvido com atraso. Multa: R$ " + usuario.getValorMulta();
    }
}
