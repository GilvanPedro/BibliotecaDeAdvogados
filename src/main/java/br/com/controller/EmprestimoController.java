package br.com.controller;

import br.com.model.entity.Emprestimo;
import br.com.model.entity.Livro;
import br.com.model.entity.Usuario;
import br.com.repository.EmprestimoRepository;
import br.com.service.EmprestimoService;

import java.util.Comparator;
import java.util.List;

public class EmprestimoController {

    EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    EmprestimoService emprestimoService = new EmprestimoService();
    UsuarioController usuarioController = new UsuarioController();
    LivroController livroController = new LivroController();

    // buscar pelo emprestimo
    public Emprestimo buscarEmprestimo(int id){
        List<Emprestimo> listaEmprestimos = emprestimoService.listarEmprestimos();

        // ordenar a lista pelo id
        listaEmprestimos.sort(Comparator.comparingInt(Emprestimo::getId));

        int left = 0;
        int right = listaEmprestimos.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Emprestimo emprestimo = listaEmprestimos.get(mid);

            if (emprestimo.getId() == id) {
                // encontrou o emprestimo
                return emprestimo;

            } else if (emprestimo.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // nao encontrou o emprestimo
        return null;
    }

    // adicionar um emprestimo
    public void adicionarEmprestimo(int id_livro, int id_usuario){
        Livro livro = livroController.buscarLivro(id_livro);
        Usuario usuario = usuarioController.buscarUsuario(id_usuario);

        Emprestimo novoEmprestimo = new Emprestimo(
                usuario,
                livro
        );

        emprestimoRepository.salvar(novoEmprestimo);
    }
}
