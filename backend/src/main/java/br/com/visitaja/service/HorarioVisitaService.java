package br.com.visitaja.service;

import br.com.visitaja.dto.horariovisita.HorarioVisitaRequestDTO;
import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.entity.Patio;
import br.com.visitaja.repository.HorarioVisitaRepository;
import br.com.visitaja.repository.PatioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorarioVisitaService {

    private final HorarioVisitaRepository horarioVisitaRepository;
    private final PatioRepository patioRepository;

    public List<HorarioVisita> listarHorariosDisponiveisPorPatio(Long patioId) {
        return horarioVisitaRepository.findByPatioIdAndVagasDisponiveisGreaterThan(patioId, 0);
    }

    @Transactional
    public HorarioVisita criarHorario(HorarioVisitaRequestDTO dto) {
        Patio patio = patioRepository.findById(dto.patioId())
                .orElseThrow(() -> new IllegalArgumentException("Pátio não encontrado com o ID fornecido."));

        HorarioVisita horario = new HorarioVisita();
        horario.setDataHora(dto.dataHora());
        horario.setVagasTotais(dto.vagasTotais());

        horario.setVagasDisponiveis(dto.vagasTotais());
        horario.setPatio(patio);

        return horarioVisitaRepository.save(horario);
    }
}