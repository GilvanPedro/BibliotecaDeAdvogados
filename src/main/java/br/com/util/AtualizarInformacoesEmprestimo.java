package br.com.util;

import br.com.model.entity.Emprestimo;
import br.com.repository.EmprestimoRepository;
import br.com.repository.HistoricoLeituraRepository;
import br.com.repository.UsuarioRepository;

public class AtualizarInformacoesEmprestimo {

    private final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public void atualizar(Emprestimo emprestimo){

        final int pontosPorLeituraConstante = 4;
        final int pegarLivroPonto = 1;

        // adiciona os pontos por ler 10 livros e por pegar um livro
        if(emprestimo.getUsuario().getLivros_alugados_total() % 10 == 0){
            usuarioRepository.adicionarPontos(emprestimo.getUsuario().getId(), pontosPorLeituraConstante);
        }

        usuarioRepository.adicionarPontos(emprestimo.getUsuario().getId(), pegarLivroPonto);

        //atualizar a quantidade do livro disponivel e a quantidade de vezes que ele foi alugado, juntamente com a quantidade de livros que o usuario ja pegou e possui no momento e coloca o livro como devolvido no emprestimo
        emprestimo.getLivro().setQuantidadeLivros(
                emprestimo.getLivro().getQuantidadeLivros() - 1
        );
        emprestimo.getLivro().setQuantidadeEmprestimo(
                emprestimo.getLivro().getQuantidadeEmprestimo() + 1
        );
        emprestimo.getUsuario().setLivros_em_posse(
                emprestimo.getUsuario().getLivros_em_posse() + 1
        );
        emprestimo.getUsuario().setLivros_alugados_total(
                emprestimo.getUsuario().getLivros_alugados_total() + 1
        );
        emprestimo.setLivro_devolvido(false);

        if(emprestimo.getUsuario().isPossui_livro() == false && emprestimo.getUsuario().getLivros_em_posse() > 0){
            emprestimo.getUsuario().setPossui_livro(true);
        }
    }
}
