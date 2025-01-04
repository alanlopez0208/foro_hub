package com.example.foro_alura.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DTORegistrarTopico(
        @NotBlank(message = "Denes de Ingresar el titulot")
        String titulo,
        @NotBlank(message = "Debes de Ingresar el mensaje")
        String mensaje,
        @NotNull(message = "Debe de Ingresar el id del Usuario, mediante {id}")
        Long id_usuario
) {
}
