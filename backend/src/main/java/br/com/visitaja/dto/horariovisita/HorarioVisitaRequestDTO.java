package br.com.visitaja.dto.horariovisita;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record HorarioVisitaRequestDTO(

        @NotNull(message = "A data e hora são obrigatórias.")
        @Future(message = "A data e hora devem estar no futuro.")
        LocalDateTime dataHora,

        @NotNull(message = "A quantidade de vagas é obrigatória.")
        @Positive(message = "A quantidade de vagas deve ser maior que zero.")
        Integer vagasTotais,

        @NotNull(message = "O pátio é obrigatório.")
        Long patioId

) {}