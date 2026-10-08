package br.com.visitaja.service;

import br.com.visitaja.dto.leilao.LeilaoCompletoRequestDTO;
import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.entity.Leilao;
import br.com.visitaja.entity.Patio;
import br.com.visitaja.enums.StatusLeilao;
import br.com.visitaja.repository.HorarioVisitaRepository;
import br.com.visitaja.repository.LeilaoRepository;
import br.com.visitaja.repository.PatioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;
    private final PatioRepository patioRepository;
    private final HorarioVisitaRepository horarioVisitaRepository;

    @Transactional
    public Leilao criarLeilaoCompleto(LeilaoCompletoRequestDTO dto) {
        Leilao leilao = new Leilao();
        leilao.setTitulo(dto.titulo());
        leilao.setDescricao(dto.descricao());
        leilao.setStatus(StatusLeilao.valueOf(dto.status()));
        leilao = leilaoRepository.save(leilao);

        if (dto.patios() != null) {
            for (LeilaoCompletoRequestDTO.PatioCompletoDTO pDto : dto.patios()) {
                Patio patio = new Patio();
                patio.setNome(pDto.nome());
                patio.setEndereco(pDto.endereco());
                patio.setLeilao(leilao);
                patio = patioRepository.save(patio);

                List<HorarioVisita> horarios = new ArrayList<>();

                for (LocalDate data = pDto.dataInicio(); !data.isAfter(pDto.dataFim()); data = data.plusDays(1)) {
                    for (LocalTime hora = pDto.horaInicio(); !hora.isAfter(pDto.horaFim()); hora = hora.plusHours(1)) {
                        HorarioVisita hv = new HorarioVisita();
                        hv.setPatio(patio);
                        hv.setDataHora(LocalDateTime.of(data, hora));
                        hv.setVagasTotais(pDto.vagasPorHorario());
                        hv.setVagasDisponiveis(pDto.vagasPorHorario()); // Vagas iniciais
                        horarios.add(hv);
                    }
                }
                horarioVisitaRepository.saveAll(horarios);
            }
        }
        return leilao;
    }
}