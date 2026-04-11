package br.com.controller;

import br.com.model.entity.Emprestimo;
import br.com.service.DevolucaoService;

public class DevolucaoController {

    EmprestimoController emprestimoController = new EmprestimoController();
    DevolucaoService devolucaoService = new DevolucaoService();

    // devolver o livro
    public void DevolverLivro(int id){
        Emprestimo emprestimo = emprestimoController.buscarEmprestimo(id);

        devolucaoService.devolverLivro(emprestimo);
    }
}
