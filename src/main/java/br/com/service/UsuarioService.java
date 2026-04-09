package br.com.service;

import br.com.model.entity.Usuario;
import br.com.model.enums.FuncaoUsuario;
import br.com.repository.UsuarioRepository;
import br.com.util.ValidacoesEmailCpf;

import java.util.Comparator;
import java.util.List;

public class UsuarioService {

    UsuarioRepository usuarioRepository = new UsuarioRepository();

    // adicionar um usuario
    public String adicionarUsuario(String nome, String cpf, String email, FuncaoUsuario funcaoUsuario){
        if(ValidacoesEmailCpf.validarCPF(cpf) == false){
            return "CPF inválido";
        }
        if(ValidacoesEmailCpf.validarEmail(email) == false){
            return "Email inválido";
        }

        Usuario novoUsuario = new Usuario(
                nome,
                cpf,
                email,
                funcaoUsuario
        );

        usuarioRepository.salvar(novoUsuario);

        return "Usuario salvo com sucesso";
    }

    // editar um usuario
    public String editarUsuario(int id, String nome, String email, FuncaoUsuario funcaoUsuario) {
        List<Usuario> listaUsuarios = usuarioRepository.listarUsuarios();

        // ordenar a lista pelo id
        listaUsuarios.sort(Comparator.comparingInt(Usuario::getId));

        int left = 0;
        int right = listaUsuarios.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Usuario u = listaUsuarios.get(mid);

            if (u.getId() == id) {
                u.setNome(nome);
                u.setEmail(email);
                u.setFuncao(funcaoUsuario);

                usuarioRepository.atualizar(u);

                return "Usuário alterado com sucesso";
            } else if (u.getId() < id) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return "Usuário não encontrado";
    }

    // inativar um usuário
    public String inativarUsuario(int id){
        usuarioRepository.inativarUsuario(id);

        return  "Usuario inativado com sucesso";
    }
}
