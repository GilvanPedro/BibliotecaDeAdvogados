package br.com.service;

import br.com.model.entity.Livro;
import br.com.model.enums.CategoriaLivros;
import br.com.repository.LivroRepository;
import br.com.util.ValidacoesEmailCpf;

import java.util.Comparator;
import java.util.List;

public class LivroService {

    LivroRepository livroRepository = new LivroRepository();

    public boolean informacoesCorretas(Livro livro){
        // verificacoes para ver se as informacoes estao vazias
        if(livro.getTitulo() == null || livro.getAutor() == null || livro.getEditora() == null){
            return false;
        }
        return true;
    }

    // salvar o livro
    public String salvarLivro(Livro livro){
        if(!informacoesCorretas(livro)){
            return "O titulo, autor e editora não podem estar vazios";
        }

        livroRepository.salvar(livro);

        return "Livro salvo com sucesso";
    }

    // editar um livro
    public String editarLivro(Livro l, String titulo, String autor, String editora, CategoriaLivros categoria) {

        if (l != null) {
            l.setTitulo(titulo);
            l.setAutor(autor);
            l.setEditora(editora);
            l.setCategoria(categoria);

            livroRepository.atualizar(l);

            return "Livro alterado com sucesso";
        }

        return "Edição falhou, verifique novamente as informações fornecidas";
    }

    // inativar livro
    public String inativarLivro(Livro livro){
        if(livro != null){
            livro.setAtivo(false);
            livroRepository.inativarLivro(livro.getId());

            return "Livro inativado com sucesso";
        }

        return "Livro não encontrado";
    }
}
