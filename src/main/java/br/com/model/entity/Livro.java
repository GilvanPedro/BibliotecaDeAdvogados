package br.com.model.entity;

import br.com.model.enums.CategoriaLivros;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private CategoriaLivros categoria;
    private int quantidadeLivros;
    private int quantidadeEmprestimo;
    private boolean ativo;

    public Livro(String titulo, String autor, String editora, CategoriaLivros categoria, int quantidadeLivros){
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.categoria = categoria;
        this.quantidadeLivros = quantidadeLivros;
        this.quantidadeEmprestimo = 0;
        this.ativo = true;
    }

    public Livro(int id, String titulo, String autor, String editora, CategoriaLivros categoria, int quantidadeLivros){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.categoria = categoria;
        this.quantidadeLivros = quantidadeLivros;
        this.quantidadeEmprestimo = 0;
        this.ativo = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public CategoriaLivros getCategoria() {
        return categoria;
    }

    public int getQuantidadeLivros() {
        return quantidadeLivros;
    }

    public int getQuantidadeEmprestimo() {
        return quantidadeEmprestimo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setCategoria(CategoriaLivros categoria) {
        this.categoria = categoria;
    }

    public void setQuantidadeLivros(int quantidadeLivros) {
        this.quantidadeLivros = quantidadeLivros;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setQuantidadeEmprestimo(int quantidadeEmprestimo) {
        this.quantidadeEmprestimo = quantidadeEmprestimo;
    }
}
