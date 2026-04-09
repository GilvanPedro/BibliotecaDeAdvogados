package br.com.util;

import br.com.model.entity.Usuario;

public class ValidarQuantidadeLivrosPegos {
    public boolean podePegarLivros(Usuario u){
        if(u.getLivros_em_posse() < u.getFuncao().getQuantidadeLivros()) return true;

        else return false;
    }
}
