package br.com.model.enums;

public enum FuncaoUsuario {
    ESTAGIARIO(7, 2),
    SECRETARIO(7, 2),
    ADVOGADO(14, 3),
    GERENTE(21, 4),
    OUTRO(7, 1);

    private int diasEmprestimo;
    private int quantidadeLivros;

    FuncaoUsuario(int diasEmprestimo, int quantidadeLivros){
        this.diasEmprestimo = diasEmprestimo;
        this.quantidadeLivros = quantidadeLivros;
    }

    public int getDiasEmprestimo(){
        return diasEmprestimo;
    }

    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }
}
