package br.com.service;

import br.com.controller.UsuarioController;
import br.com.model.entity.Usuario;
import br.com.model.enums.FuncaoUsuario;
import br.com.repository.UsuarioRepository;
import br.com.util.ValidacoesEmailCpf;

public class UsuarioService {

    UsuarioRepository usuarioRepository = new UsuarioRepository();
    UsuarioController usuarioController = new UsuarioController();

    // adicionar um usuario
    public String adicionarUsuario(Usuario usuario){
        if(usuario.getNome() == null){
            return "Nome inválido";
        }
        if(ValidacoesEmailCpf.validarCPF(usuario.getCpf()) == false){
            return "CPF inválido";
        }
        if(ValidacoesEmailCpf.validarEmail(usuario.getEmail()) == false){
            return "Email inválido";
        }

        usuarioRepository.salvar(usuario);

        return "Usuario salvo com sucesso";
    }

    // editar um usuario
    public String editarUsuario(int id, String nome, String email, FuncaoUsuario funcaoUsuario) {
        Usuario usuario = usuarioController.buscarUsuario(id);

        if(id <= 0){
            return "Id inválido";
        }
        if(nome == null || !ValidacoesEmailCpf.validarEmail(email)){
            return "email ou nome inválidos";
        }

        if(usuario.getId() == id){
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setFuncao(funcaoUsuario);

            usuarioRepository.atualizar(usuario);

            return "Usuario editado com sucesso";

        } else{
            return "usuario não encontrado";
        }
    }

    // inativar um usuário
    public String inativarUsuario(Usuario usuario){

        if(usuario != null){
            usuario.setAtivo(false);
            usuarioRepository.inativarUsuario(usuario.getId());

            return  "Usuario inativado com sucesso";
        }

        return "Usuario não encontrado!";
    }

    // adicionar pontos para os usuarios
    public String adicionarPontos(Usuario usuario, int pontos){
        if(!usuario.isAtivo()){
            return "Usuario não esta ativo, impossível adicionar pontos para ele";
        }

        if(pontos <= 0){
            return "Você precisa adiconar pontos maior do que 0";
        }

        usuario.setPontos(usuario.getPontos() + pontos);
        usuarioRepository.adicionarPontos(usuario.getId(), pontos);

        return "Pontos adicionados com sucesso para "+usuario.getNome();
    }

    // adicionar as multas
    public String adicionarMulta(int id, int multa){
        Usuario usuario = usuarioController.buscarUsuario(id);

        if(usuario == null){
            return "Usuario não encontrado";
        }
        if(multa<=0){
            return "A multa precisa ser maior do que 0";
        }

        usuario.setValorMulta(usuario.getValorMulta()+multa);
        usuario.setMulta_pendente(true);
        usuarioRepository.atualizarStatusMulta(usuario);

        return "Multa adicionada com sucesso";
    }

    // pagar as multas
    public String pagarMulta(Usuario usuario){
        if(usuario.getValorMulta() <= 0 && !usuario.isMulta_pendente()){
            return "Usuário não possui nenhuma multa pendente";
        }

        usuario.setValorMulta(0);
        usuario.setMulta_pendente(false);
        usuarioRepository.atualizarStatusMulta(usuario);

        return "Multa Paga com Sucesso";
    }
}
