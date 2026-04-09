package br.com.model.entity;

import br.com.model.enums.FuncaoUsuario;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private FuncaoUsuario funcao;
    private long livros_alugados_total;
    private boolean multa_pendente;
    private int livros_em_posse;
    private boolean possui_livro;
    private int pontos;
    private boolean ativo;
    private int valorMulta;

    public Usuario(String nome, String cpf, String email, FuncaoUsuario funcao){
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.funcao = funcao;
        this.livros_alugados_total = 0;
        this.multa_pendente = false;
        this.possui_livro = false;
        this.pontos = 0;
        this.livros_em_posse = 0;
        this.ativo = true;
        this.valorMulta = 0;
    }

    public Usuario(int id, String nome, String cpf, String email, FuncaoUsuario funcao){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.funcao = funcao;
        this.livros_alugados_total = 0;
        this.multa_pendente = false;
        this.possui_livro = false;
        this.livros_em_posse = 0;
        this.pontos = 0;
        this.ativo = true;
        this.valorMulta = 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public FuncaoUsuario getFuncao() {
        return funcao;
    }

    public long getLivros_alugados_total() {
        return livros_alugados_total;
    }

    public boolean isMulta_pendente() {
        return multa_pendente;
    }

    public int getLivros_em_posse() {
        return livros_em_posse;
    }

    public int getValorMulta() {
        return valorMulta;
    }

    public boolean isPossui_livro() {
        return possui_livro;
    }

    public int getPontos() {
        return pontos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFuncao(FuncaoUsuario funcao) {
        this.funcao = funcao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setMulta_pendente(boolean multa_pendente) {
        this.multa_pendente = multa_pendente;
    }

    public void setPossui_livro(boolean possui_livro) {
        this.possui_livro = possui_livro;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setLivros_alugados_total(long livros_alugados_total) {
        this.livros_alugados_total = livros_alugados_total;
    }

    public void setLivros_em_posse(int livros_em_posse) {
        this.livros_em_posse = livros_em_posse;
    }

    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }
}
