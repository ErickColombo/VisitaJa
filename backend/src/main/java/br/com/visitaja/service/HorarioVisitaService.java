package br.com.visitaja.service;

import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.repository.HorarioVisitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioVisitaService {

    private final HorarioVisitaRepository horarioVisitaRepository;

    public List<HorarioVisita> listarHorariosDisponiveisPorPatio(Long patioId) {
        // Retorna apenas os horários cujo número de vagas disponíveis é maior que 0
        return horarioVisitaRepository.findByPatioIdAndVagasDisponiveisGreaterThan(patioId, 0);
    }
}