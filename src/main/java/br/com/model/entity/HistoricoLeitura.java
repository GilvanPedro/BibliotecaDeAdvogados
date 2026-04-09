package br.com.model.entity;

import br.com.model.enums.StatusLeitura;
import br.com.model.enums.TipoLeitura;

public class HistoricoLeitura {
    private int id;
    private Usuario usuario;
    private Livro livro;
    private Emprestimo emprestimo;
    private TipoLeitura tipoLeitura;
    private StatusLeitura statusLeitura;

    public HistoricoLeitura(Usuario usuario, Livro livro, Emprestimo emprestimo, TipoLeitura tipoLeitura, StatusLeitura statusLeitura){
        this.usuario = usuario;
        this.livro = livro;
        this.emprestimo = emprestimo;
        this.tipoLeitura = tipoLeitura;
        this.statusLeitura = statusLeitura;
    }

    public HistoricoLeitura(int id, Usuario usuario, Livro livro, Emprestimo emprestimo, TipoLeitura tipoLeitura, StatusLeitura statusLeitura){
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.emprestimo = emprestimo;
        this.tipoLeitura = tipoLeitura;
        this.statusLeitura = statusLeitura;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public TipoLeitura getTipoLeitura() {
        return tipoLeitura;
    }

    public StatusLeitura getStatusLeitura() {
        return statusLeitura;
    }

    public void setStatusLeitura(StatusLeitura statusLeitura) {
        this.statusLeitura = statusLeitura;
    }

    public void setTipoLeitura(TipoLeitura tipoLeitura) {
        this.tipoLeitura = tipoLeitura;
    }
}
