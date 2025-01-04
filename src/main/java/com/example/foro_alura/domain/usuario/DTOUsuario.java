package com.example.foro_alura.domain.usuario;

public record DTOUsuario(
        String nombre
) {

    public DTOUsuario(Usuario usuario){
        this(usuario.getNombre());
    }
}
