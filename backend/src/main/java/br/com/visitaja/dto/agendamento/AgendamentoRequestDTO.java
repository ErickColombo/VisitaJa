package br.com.visitaja.dto.agendamento;

import br.com.visitaja.dto.visitante.VisitanteRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AgendamentoRequestDTO(

        @NotNull(message = "O horário de visita é obrigatório.")
        Long horarioId,

        @NotNull(message = "Os dados do visitante são obrigatórios.")
        @Valid
        VisitanteRequestDTO visitante

) {}