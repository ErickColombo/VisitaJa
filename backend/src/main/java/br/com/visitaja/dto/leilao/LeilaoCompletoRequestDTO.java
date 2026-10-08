package br.com.visitaja.dto.leilao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record LeilaoCompletoRequestDTO(
        String titulo,
        String descricao,
        String status,
        List<PatioCompletoDTO> patios
) {
    public record PatioCompletoDTO(
            String nome,
            String endereco,
            LocalDate dataInicio,
            LocalDate dataFim,
            LocalTime horaInicio,
            LocalTime horaFim,
            Integer vagasPorHorario
    ) {}
}