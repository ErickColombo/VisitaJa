package br.com.visitaja.dto.leilao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LeilaoRequestDTO(

        @NotBlank(message = "O título é obrigatório.")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres.")
        String titulo,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres.")
        String descricao

) {}