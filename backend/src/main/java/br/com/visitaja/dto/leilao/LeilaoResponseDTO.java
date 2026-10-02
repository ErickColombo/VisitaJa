package br.com.visitaja.dto.leilao;

import br.com.visitaja.enums.StatusLeilao;

public record LeilaoResponseDTO(

        Long id,
        String titulo,
        String descricao,
        StatusLeilao status

) {}