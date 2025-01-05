package com.example.foro_alura.domain.dto.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTOActualizarTopico(
        @NotBlank(message = "Debes de ingresar el titulo")
        String titulo,
        @NotBlank(message = "Debes de ingresar el mensaje")
        String mensaje,
        @NotNull(message = "Debes de Ingresar el Status")
        Boolean status,
        @JsonAlias("fecha_creacion")
        @NotNull(message = "Debes de ingresar la fecha")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime fechaCreacion,
        @JsonAlias("id_usuario")
        Long idUsuario
) {
}
