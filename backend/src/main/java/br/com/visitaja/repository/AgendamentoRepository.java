package br.com.visitaja.repository;

import br.com.visitaja.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    // Histórico de agendamentos de um visitante
    List<Agendamento> findByVisitanteId(Long visitanteId);

    // Lista de pessoas que vão em um determinado horário
    List<Agendamento> findByHorarioId(Long horarioId);

    // Valida se o visitante já agendou aquele horário específico
    boolean existsByVisitanteIdAndHorarioId(Long visitanteId, Long horarioId);
}