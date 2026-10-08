package br.com.visitaja.repository;

import br.com.visitaja.entity.HorarioVisita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioVisitaRepository extends JpaRepository<HorarioVisita, Long> {
    List<HorarioVisita> findByPatioId(Long patioId);

    List<HorarioVisita> findByPatioIdAndVagasDisponiveisGreaterThan(Long patioId, Integer vagas);
}