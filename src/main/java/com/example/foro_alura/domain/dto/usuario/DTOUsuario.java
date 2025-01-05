package com.example.foro_alura.domain.dto.usuario;

import com.example.foro_alura.domain.entity.usuario.Usuario;

public record DTOUsuario(
        String nombre
) {

    public DTOUsuario(Usuario usuario){
        this(usuario.getNombre());
    }
}
