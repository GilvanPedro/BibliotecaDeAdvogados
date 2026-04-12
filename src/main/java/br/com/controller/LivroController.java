package br.com.controller;

import br.com.model.entity.Livro;
import br.com.model.enums.CategoriaLivros;
import br.com.repository.LivroRepository;
import br.com.service.LivroService;

import java.util.Comparator;
import java.util.List;

public class LivroController {

    LivroRepository livroRepository = new LivroRepository();
    LivroService livroService = new LivroService();

    // buscar por livro
    public Livro buscarLivro(int id){
        List<Livro> listaLivros = livroRepository.listar();

        // ordenar a lista pelo id
        listaLivros.sort(Comparator.comparingInt(Livro::getId));

        int left = 0;
        int right = listaLivros.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Livro livro = listaLivros.get(mid);

            if (livro.getId() == id) {
                // encontrou o usuario
                return livro;

            } else if (livro.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // nao encontrou o livro
        return null;
    }

    // adicionar um livro
    public void adicionarLivro(String titulo, String autor, String editora, CategoriaLivros categoria, int quantidade){

        Livro novoLivro = new Livro(
                titulo,
                autor,
                editora,
                categoria,
                quantidade
        );

        livroService.salvarLivro(novoLivro);
    }

    // inativar um livro
    public void inativarLivro(int id){
        Livro livro = buscarLivro(id);

        livroService.inativarLivro(livro);
    }

    // editar o livro
    public void editarLivro(int id, String titulo, String autor, String editora, CategoriaLivros categoria){

        Livro livro = buscarLivro(id);

        livroService.editarLivro(livro, titulo, autor, editora, categoria);
    }
}
