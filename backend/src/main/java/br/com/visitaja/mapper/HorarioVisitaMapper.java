package br.com.visitaja.mapper;

import br.com.visitaja.dto.horariovisita.HorarioVisitaRequestDTO;
import br.com.visitaja.dto.horariovisita.HorarioVisitaResponseDTO;
import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.entity.Patio;

public class HorarioVisitaMapper {

    private HorarioVisitaMapper() {}

    public static HorarioVisita toEntity(HorarioVisitaRequestDTO dto, Patio patio) {
        HorarioVisita horario = new HorarioVisita();
        horario.setDataHora(dto.dataHora());
        horario.setVagasTotais(dto.vagasTotais());
        horario.setVagasDisponiveis(dto.vagasTotais());
        horario.setPatio(patio);
        return horario;
    }

    public static void updateEntityFromDTO(HorarioVisitaRequestDTO dto, HorarioVisita horario, Patio patio) {
        horario.setDataHora(dto.dataHora());
        horario.setVagasTotais(dto.vagasTotais());
        horario.setPatio(patio);
    }

        public static HorarioVisitaResponseDTO toResponseDTO(HorarioVisita horario) {
        return new HorarioVisitaResponseDTO(
                horario.getId(),
                horario.getDataHora(),
                horario.getVagasTotais(),
                horario.getVagasDisponiveis(),
                horario.getPatio().getId(),
                horario.getPatio().getNome(),
                horario.getPatio().getLeilao().getTitulo()
        );
    }
}