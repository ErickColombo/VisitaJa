package br.com.visitaja.dto.visitante;

public record VisitanteResponseDTO(

        Long id,
        String nome,
        String cgc,
        String email,
        String telefone

) {}