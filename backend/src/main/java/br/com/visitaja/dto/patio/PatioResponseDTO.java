package br.com.visitaja.dto.patio;

public record PatioResponseDTO(

        Long id,
        String nome,
        String endereco,
        Long leilaoId,
        String leilaoTitulo

) {}