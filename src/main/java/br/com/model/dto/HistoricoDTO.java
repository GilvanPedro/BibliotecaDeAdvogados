package br.com.model.dto;

import br.com.model.enums.StatusLeitura;
import br.com.model.enums.TipoLeitura;

import java.time.LocalDate;

public class HistoricoDTO {
    private int id;
    private String usuarioNome;
    private String usuarioCpf;
    private String usuarioFuncao;
    private String livroTitulo;
    private String livroAutor;
    private String livroCategoria;
    private LocalDate emprestimoData;
    private LocalDate devolucaoData;
    private boolean livroDevolvido;
    private TipoLeitura tipoLeitura;
    private StatusLeitura statusLeitura;

    public HistoricoDTO(int id, String usuarioNome, String usuarioCpf, String usuarioFuncao, String livroTitulo, String livroAutor, String livroCategoria, LocalDate emprestimoData, LocalDate devolucaoData, boolean livroDevolvido, TipoLeitura tipoLeitura, StatusLeitura statusLeitura) {
        this.id = id;
        this.usuarioNome = usuarioNome;
        this.usuarioCpf = usuarioCpf;
        this.usuarioFuncao = usuarioFuncao;
        this.livroTitulo = livroTitulo;
        this.livroAutor = livroAutor;
        this.livroCategoria = livroCategoria;
        this.emprestimoData = emprestimoData;
        this.devolucaoData = devolucaoData;
        this.livroDevolvido = livroDevolvido;
        this.tipoLeitura = tipoLeitura;
        this.statusLeitura = statusLeitura;
    }

    public int getId() {
        return id;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public String getUsuarioCpf() {
        return usuarioCpf;
    }

    public String getUsuarioFuncao() {
        return usuarioFuncao;
    }

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public String getLivroAutor() {
        return livroAutor;
    }

    public String getLivroCategoria() {
        return livroCategoria;
    }

    public LocalDate getEmprestimoData() {
        return emprestimoData;
    }

    public LocalDate getDevolucaoData() {
        return devolucaoData;
    }

    public TipoLeitura getTipoLeitura() {
        return tipoLeitura;
    }

    public StatusLeitura getStatusLeitura() {
        return statusLeitura;
    }

    public boolean isLivroDevolvido() {
        return livroDevolvido;
    }
}
