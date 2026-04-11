package br.com.service;

import br.com.controller.EmprestimoController;
import br.com.model.dto.HistoricoDTO;
import br.com.model.entity.*;
import br.com.model.enums.*;
import br.com.repository.HistoricoLeituraRepository;

import java.util.ArrayList;
import java.util.List;

public class HistoricoLeituraService {

    HistoricoLeituraRepository historicoRepository = new HistoricoLeituraRepository();
    HistoricoLeituraService historicoLeituraService = new HistoricoLeituraService();
    EmprestimoController emprestimoController = new EmprestimoController();

    // salva o historico
    public String salvarHistorico(HistoricoLeitura historico){
        historicoRepository.salvar(historico);
        return "Historico salvo com sucesso";
    }

    // atualizar o status para devolvido
    public String atualizarStatusParaDevolvido(HistoricoLeitura historico) {

        if (historico != null) {
            historico.setStatusLeitura(StatusLeitura.DEVOLVIDO);
            historicoRepository.atualizar(historico.getEmprestimo(), StatusLeitura.DEVOLVIDO);
            return "Status alterado com sucesso";
        }

        return "Falha ao alterar o status";
    }

    // listar o historicoDTO
    public List<HistoricoDTO> listarHistoricoDTO(){
        return historicoRepository.listar();
    }

    // listar a entidade do historico
    public List<HistoricoLeitura> listarHistoricoEntity() {

        List<HistoricoLeitura> lista = new ArrayList<>();

        for (HistoricoDTO dto : historicoRepository.listar()) {
            lista.add(converterDTOParaEntity(dto));
        }

        return lista;
    }

    // CONVERSOR
    private HistoricoLeitura converterDTOParaEntity(HistoricoDTO dto) {

        FuncaoUsuario funcao = FuncaoUsuario.valueOf(dto.getUsuarioFuncao().toUpperCase());
        CategoriaLivros categoria = CategoriaLivros.valueOf(dto.getLivroCategoria().toUpperCase());

        Usuario usuario = new Usuario(0, dto.getUsuarioNome(), dto.getUsuarioCpf(), null, funcao);

        Livro livro = new Livro(0, dto.getLivroTitulo(), dto.getLivroAutor(), null, categoria, 0);

        Emprestimo emprestimo = new Emprestimo(0, usuario, livro);

        emprestimo.setEmprestimo_data(dto.getEmprestimoData());
        emprestimo.setDevolucao_data(dto.getDevolucaoData());
        emprestimo.setLivro_devolvido(dto.isLivroDevolvido());

        return new HistoricoLeitura(
                dto.getId(),
                usuario,
                livro,
                emprestimo,
                dto.getTipoLeitura(),
                dto.getStatusLeitura()
        );
    }

    // buscar historico pelo id do emprestimo
    public HistoricoLeitura buscarHistoricoPorEmprestimoId(int emprestimoId) {

        HistoricoDTO dto = historicoRepository.buscarPorEmprestimoId(emprestimoId);

        if (dto == null) {
            return null;
        }

        return converterDTOParaEntity(dto);
    }
}