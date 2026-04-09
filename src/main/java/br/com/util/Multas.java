package br.com.util;

import br.com.model.entity.Emprestimo;
import br.com.model.entity.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Multas {
    public int calcularMulta(Emprestimo emprestimo){
        LocalDate hoje = LocalDate.now();
        LocalDate dataPrevista = emprestimo.getDevolucao_data();

        int diasAtraso = Math.toIntExact(ChronoUnit.DAYS.between(dataPrevista, hoje));

        int taxaFixa = 15;
        int valorDiario;

        if (diasAtraso <= 10) {
            valorDiario = diasAtraso * 5;
        } else {
            // Após 10 dias, a taxa passa a ser 10 reais por cada dia de atraso
            valorDiario = diasAtraso * 10;
        }

        return taxaFixa + valorDiario;
    }

    public String pagarMulta(Usuario usuario){
        if(usuario.getValorMulta() <= 0){
            return "Usuário não possui nenhuma multa pendente";
        }

        usuario.setValorMulta(0);
        return "Multa Paga com Sucesso";
    }
}
