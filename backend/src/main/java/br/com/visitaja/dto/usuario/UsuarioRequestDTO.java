package br.com.visitaja.dto.usuario;

import br.com.visitaja.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(

        @NotBlank(message = "O login é obrigatório.")
        @Size(max = 100, message = "O login deve ter no máximo 100 caracteres.")
        String user,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        String senha,

        @NotNull(message = "O papel (role) é obrigatório.")
        Role role

) {}