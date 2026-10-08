package br.com.visitaja.dto.horariovisita;

import java.time.LocalDateTime;

public record HorarioVisitaResponseDTO(

        Long id,
        LocalDateTime dataHora,
        Integer vagasTotais,
        Integer vagasDisponiveis,
        Long patioId,
        String patioNome,
        String leilaoTitulo
) {}