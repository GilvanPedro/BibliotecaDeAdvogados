package br.com.controller;

import br.com.model.entity.HistoricoLeitura;
import br.com.service.DevolucaoService;

public class DevolucaoController {

    HistoricoLeituraController historicoLeituraController = new HistoricoLeituraController();
    DevolucaoService devolucaoService = new DevolucaoService();

    // devolver o livro
    public void DevolverLivro(int id){
        HistoricoLeitura historicoLeitura = historicoLeituraController.buscarHistorico(id);

        devolucaoService.devolverLivro(historicoLeitura);
    }
}
