package br.com.visitaja.dto.patio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PatioRequestDTO(

        @NotBlank(message = "O nome do pátio é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @NotBlank(message = "O endereço é obrigatório.")
        @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres.")
        String endereco,

        @NotNull(message = "O leilão é obrigatório.")
        Long leilaoId

) {}