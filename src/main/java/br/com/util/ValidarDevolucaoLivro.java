package br.com.util;

import br.com.model.entity.Emprestimo;

import java.time.LocalDate;

public class ValidarDevolucaoLivro {

    public boolean estaAtrasado(LocalDate dataPrevista) {
        return LocalDate.now().isAfter(dataPrevista);
    }

    public boolean devolvidoEmDia(Emprestimo emprestimo) {
        if (estaAtrasado(emprestimo.getDevolucao_data())) {
            // se passou do prazo marca que o usuario tem uma multa pendente
            emprestimo.getUsuario().setMulta_pendente(true);
            return false;
        }

        return true;
    }
}
