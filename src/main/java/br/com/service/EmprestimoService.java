package br.com.service;

import br.com.model.dto.EmprestimoDTO;
import br.com.model.dto.HistoricoDTO;
import br.com.model.entity.Emprestimo;
import br.com.model.entity.HistoricoLeitura;
import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.model.enums.CategoriaLivros;
import br.com.model.enums.FuncaoUsuario;
import br.com.model.enums.StatusLeitura;
import br.com.model.enums.TipoLeitura;
import br.com.repository.EmprestimoRepository;
import br.com.repository.HistoricoLeituraRepository;
import br.com.repository.UsuarioRepository;

import br.com.util.AtualizarInformacoesEmprestimo;

import br.com.util.ValidarQuantidadeLivrosPegos;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {

    private final ValidarQuantidadeLivrosPegos validacaoQuantidadeLivros = new ValidarQuantidadeLivrosPegos();
    private final EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    private final HistoricoLeituraRepository historicoRepository = new HistoricoLeituraRepository();
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    UsuarioService usuarioService = new UsuarioService();


    private final int leituraPresencialPonto = 3;

    // pegar um livro emprestado
    public String adicionarEmprestimo(Emprestimo emprestimo) {
        // verifica se o usuario tem multa pendente
        if (emprestimo.getUsuario().isMulta_pendente()) {
            return "Pague a multa no valor de R$ " + emprestimo.getUsuario().getValorMulta() + " antes de pegar outro livro!";
        }

        // verifica se o usuário ou o livro não estao ativos
        if(!emprestimo.getUsuario().isAtivo() || !emprestimo.getLivro().isAtivo()){
            return "Usuário ou Livro inativo, não é possivel realizar o emprestado";
        }

        // verifica se o usuario pode pegar mais livros
        if (!validacaoQuantidadeLivros.podePegarLivros(emprestimo.getUsuario())) {
            return "Usuário não pode pegar mais livros";
        }

        emprestimoRepository.salvar(emprestimo);

        // Caso o livro só tenha uma unidade, leitura apenas presencial
        if (emprestimo.getLivro().getQuantidadeLivros() == 1) {
            HistoricoLeitura historicoLeitura = new HistoricoLeitura(
                    emprestimo.getUsuario(),
                    emprestimo.getLivro(),
                    emprestimo,
                    TipoLeitura.PRESENCIAL,
                    StatusLeitura.LENDO
            );

            historicoRepository.salvar(historicoLeitura);
            usuarioService.adicionarPontos(emprestimo.getUsuario(), leituraPresencialPonto);

            emprestimo.getLivro().setQuantidadeEmprestimo(
                    emprestimo.getLivro().getQuantidadeEmprestimo() + 1
            );
            emprestimo.getUsuario().setLivros_alugados_total(
                    emprestimo.getUsuario().getLivros_alugados_total() + 1
            );

            return "Apenas Leitura Presencial";
        }

        // Caso empréstimo normal
        HistoricoLeitura historicoLeitura = new HistoricoLeitura(
                emprestimo.getUsuario(),
                emprestimo.getLivro(),
                emprestimo,
                TipoLeitura.EMPRESTIMO,
                StatusLeitura.LENDO
        );
        historicoRepository.salvar(historicoLeitura);

        // atualizando as informações do emprestimo do livro normal
        AtualizarInformacoesEmprestimo atualizarInformacoes = new AtualizarInformacoesEmprestimo();
        atualizarInformacoes.atualizar(emprestimo);

        return "Livro Pego com sucesso";
    }

    // conversor
    private Emprestimo converterDTOParaEntity(EmprestimoDTO dto) {

        FuncaoUsuario funcao = FuncaoUsuario.valueOf(dto.getUsuarioFuncao().toUpperCase());
        CategoriaLivros categoria = CategoriaLivros.valueOf(dto.getLivroCategoria().toUpperCase());

        Usuario usuario = new Usuario(
                0,
                dto.getUsuarioNome(),
                dto.getUsuarioCpf(),
                null,
                funcao
        );

        Livro livro = new Livro(
                0,
                dto.getLivroTitulo(),
                dto.getLivroAutor(),
                null,
                categoria,
                0
        );

        Emprestimo emprestimo = new Emprestimo(
                dto.getId(),
                usuario,
                livro
        );

        emprestimo.setEmprestimo_data(dto.getEmprestimoData());
        emprestimo.setDevolucao_data(dto.getDevolucaoData());
        emprestimo.setLivro_devolvido(dto.isLivroDevolvido());

        return emprestimo;
    }

    // lista da entidade emprestimo
    public List<Emprestimo> listarEmprestimos() {

        List<Emprestimo> lista = new ArrayList<>();

        for (EmprestimoDTO dto : emprestimoRepository.listarEmprestimoDTO()) {
            lista.add(converterDTOParaEntity(dto));
        }

        return lista;
    }
}