package br.com.model.dto;

import java.time.LocalDate;

public class EmprestimoDTO {
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

    public EmprestimoDTO(int id, String usuarioNome, String usuarioCpf, String usuarioFuncao, String livroTitulo, String livroAutor, String livroCategoria, LocalDate emprestimoData, LocalDate devolucaoData, boolean livroDevolvido) {
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

    public boolean isLivroDevolvido() {
        return livroDevolvido;
    }
}