package br.com.visitaja.repository;

import br.com.visitaja.entity.HorarioVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioVisitaRepository extends JpaRepository<HorarioVisita, Long> {
    // Busca os horários de um pátio específico
    List<HorarioVisita> findByPatioId(Long patioId);

    // Busca horários de um pátio que ainda tenham vagas disponíveis
    List<HorarioVisita> findByPatioIdAndVagasDisponiveisGreaterThan(Long patioId, Integer vagas);
}