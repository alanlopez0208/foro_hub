package com.example.foro_alura.domain.topico;

import com.example.foro_alura.domain.usuario.DTOUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DTOTopico(
        String titulo,
        String mensaje,
        @JsonProperty("fecha_creacion")
        LocalDateTime fechaCreacion,

        boolean estado,
        DTOUsuario autor
) {
    public DTOTopico(Topico t) {
        this( t.getTitulo(), t.getMensaje(), t.getFechaCreacion(), t.isStatus() ,new DTOUsuario(t.getUsuario()));
    }
}
