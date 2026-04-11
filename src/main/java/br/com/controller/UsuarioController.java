package br.com.controller;

import br.com.model.entity.Usuario;
import br.com.model.enums.FuncaoUsuario;
import br.com.repository.UsuarioRepository;
import br.com.service.UsuarioService;

import java.util.Comparator;
import java.util.List;

public class UsuarioController {

    UsuarioService usuarioService = new UsuarioService();
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    // buscar usuario
    public Usuario buscarUsuario(int id){
        List<Usuario> listaUsuarios = usuarioRepository.listarUsuarios();

        // ordenar a lista pelo id
        listaUsuarios.sort(Comparator.comparingInt(Usuario::getId));

        int left = 0;
        int right = listaUsuarios.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Usuario usuario = listaUsuarios.get(mid);

            if (usuario.getId() == id) {
                // encontrou o usuario
                return usuario;

            } else if (usuario.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // nao encontrou o usuario
        return null;
    }

    // adicionar usuario
    public void adicionarUsuario(String nome, String cpf, String email, FuncaoUsuario funcaoUsuario){

        Usuario novoUsuario = new Usuario(
                nome,
                cpf,
                email,
                funcaoUsuario
        );

        usuarioService.adicionarUsuario(novoUsuario);
    }

    // editar um usuario
    public void editarUsuario(int id, String nome, String email, FuncaoUsuario funcaoUsuario){

        usuarioService.editarUsuario(id, nome, email, funcaoUsuario);
    }

    // inativar um usuario
    public void inativarUsuario(int id){
        Usuario usuario = buscarUsuario(id);

        usuarioService.inativarUsuario(usuario);
    }

    // adicionar os pontos para o usuario
    public void adicionarPontos(int id, int pontos){
        Usuario usuario = buscarUsuario(id);

        usuarioService.adicionarPontos(usuario, pontos);
    }
}
