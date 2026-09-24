package br.com.visitaja.service;

import br.com.visitaja.entity.Agendamento;
import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.entity.Visitante;
import br.com.visitaja.enums.StatusAgendamento;
import br.com.visitaja.repository.AgendamentoRepository;
import br.com.visitaja.repository.HorarioVisitaRepository;
import br.com.visitaja.repository.VisitanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final HorarioVisitaRepository horarioVisitaRepository;
    private final VisitanteRepository visitanteRepository;

    @Transactional
    public Agendamento realizarAgendamento(Long visitanteId, Long horarioId) {

        Visitante visitante = visitanteRepository.findById(visitanteId)
                .orElseThrow(() -> new IllegalArgumentException("Visitante não encontrado."));

        HorarioVisita horario = horarioVisitaRepository.findById(horarioId)
                .orElseThrow(() -> new IllegalArgumentException("Horário de visita não encontrado."));

        // 1. Valida se o visitante já está agendado neste horário
        if (agendamentoRepository.existsByVisitanteIdAndHorarioId(visitanteId, horarioId)) {
            throw new IllegalStateException("Visitante já possui agendamento para este horário.");
        }

        // 2. Valida se há vagas disponíveis
        if (horario.getVagasDisponiveis() <= 0) {
            throw new IllegalStateException("Não há vagas disponíveis para este horário.");
        }

        // 3. Decrementa a quantidade de vagas disponíveis
        horario.setVagasDisponiveis(horario.getVagasDisponiveis() - 1);
        horarioVisitaRepository.save(horario);

        // 4. Cria e salva o registro do agendamento
        Agendamento agendamento = new Agendamento();
        agendamento.setVisitante(visitante);
        agendamento.setHorario(horario);
        agendamento.setDataRegistro(LocalDateTime.now());
        agendamento.setStatus(StatusAgendamento.CONFIRMADO); // Substitua pelo enum correto caso seja outro

        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public void cancelarAgendamento(Long agendamentoId) {

        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado."));

        if (agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new IllegalStateException("Este agendamento já se encontra cancelado.");
        }

        // 1. Atualiza o status
        agendamento.setStatus(StatusAgendamento.CANCELADO);
        agendamentoRepository.save(agendamento);

        // 2. Devolve a vaga para o horário
        HorarioVisita horario = agendamento.getHorario();
        horario.setVagasDisponiveis(horario.getVagasDisponiveis() + 1);
        horarioVisitaRepository.save(horario);
    }
}