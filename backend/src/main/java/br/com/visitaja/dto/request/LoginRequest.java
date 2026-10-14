package br.com.visitaja.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "Usuário é obrigatório")
        String username,

        @NotEmpty(message = "Senha é obrigatória")
        String password) {
}
