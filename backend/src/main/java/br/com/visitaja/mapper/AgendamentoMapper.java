package br.com.visitaja.mapper;

import br.com.visitaja.dto.agendamento.AgendamentoResponseDTO;
import br.com.visitaja.entity.Agendamento;
import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.entity.Visitante;
import br.com.visitaja.enums.StatusAgendamento;

import java.time.LocalDateTime;

public class AgendamentoMapper {

    private AgendamentoMapper() {}

    public static Agendamento toEntity(HorarioVisita horario, Visitante visitante) {
        Agendamento agendamento = new Agendamento();
        agendamento.setHorario(horario);
        agendamento.setVisitante(visitante);
        agendamento.setDataRegistro(LocalDateTime.now());
        agendamento.setStatus(StatusAgendamento.PENDENTE);
        return agendamento;
    }

    public static AgendamentoResponseDTO toResponseDTO(Agendamento agendamento) {
        return new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDataRegistro(),
                agendamento.getStatus(),
                HorarioVisitaMapper.toResponseDTO(agendamento.getHorario()),
                VisitanteMapper.toResponseDTO(agendamento.getVisitante())
        );
    }
}