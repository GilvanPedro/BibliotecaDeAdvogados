package br.com.service;

import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.model.enums.CategoriaLivros;
import br.com.model.enums.FuncaoUsuario;
import br.com.repository.LivroRepository;

import java.util.Comparator;
import java.util.List;

public class LivroService {

    LivroRepository livroRepository = new LivroRepository();

    public boolean informacoesCorretas(Livro livro){
        // verificacoes para ver se as informacoes estao vazias
        if(livro.getTitulo().equals(null) || livro.getAutor().equals(null) || livro.getEditora().equals(null)){
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
    public String editarLivro(int id, String titulo, String autor, String editora, CategoriaLivros categoria) {
        List<Livro> listaLivros = livroRepository.listar();

        // ordenar a lista pelo id
        listaLivros.sort(Comparator.comparingInt(Livro::getId));

        int left = 0;
        int right = listaLivros.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Livro l = listaLivros.get(mid);

            if (l.getId() == id) {
                l.setTitulo(titulo);
                l.setAutor(autor);
                l.setEditora(editora);
                l.setCategoria(categoria);

                livroRepository.atualizar(l);

                return "Livro alterado com sucesso";
            } else if (l.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Livro não encontrado";
    }

    public String inativarLivro(int id){
        inativarLivro(id);

        return "Livro inativado com sucesso";
    }
}
