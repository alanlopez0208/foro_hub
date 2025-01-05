package com.example.foro_alura.domain.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOAuth(
        @NotBlank
        String correo,

        @NotBlank
        String password
) {
}
