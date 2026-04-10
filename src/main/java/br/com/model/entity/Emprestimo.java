package br.com.model.entity;

import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private Usuario usuario;
    private Livro livro;
    private LocalDate emprestimo_data;
    private LocalDate devolucao_data;
    private boolean livro_devolvido;

    public Emprestimo(Usuario usuario, Livro livro){
        this.usuario = usuario;
        this.livro = livro;
        this.emprestimo_data = LocalDate.now();
        this.devolucao_data = emprestimo_data.plusDays(usuario.getFuncao().getDiasEmprestimo());
        this.livro_devolvido = false;
    }

    public Emprestimo(int id, Usuario usuario, Livro livro){
        this.id = id;
        this.usuario = usuario;
        this.livro = livro;
        this.emprestimo_data = LocalDate.now();
        this.devolucao_data = emprestimo_data.plusDays(usuario.getFuncao().getDiasEmprestimo());
        this.livro_devolvido = false;
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

    public LocalDate getEmprestimo_data() {
        return emprestimo_data;
    }

    public LocalDate getDevolucao_data() {
        return devolucao_data;
    }

    public boolean isLivro_devolvido() {
        return livro_devolvido;
    }

    public void setLivro_devolvido(boolean livro_devolvido) {
        this.livro_devolvido = livro_devolvido;
    }
}
