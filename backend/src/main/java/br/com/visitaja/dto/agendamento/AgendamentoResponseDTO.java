package br.com.visitaja.dto.agendamento;

import br.com.visitaja.dto.horariovisita.HorarioVisitaResponseDTO;
import br.com.visitaja.dto.visitante.VisitanteResponseDTO;
import br.com.visitaja.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(

        Long id,
        LocalDateTime dataRegistro,
        StatusAgendamento status,
        HorarioVisitaResponseDTO horario,
        VisitanteResponseDTO visitante

) {}