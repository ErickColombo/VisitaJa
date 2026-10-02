package br.com.visitaja.mapper;

import br.com.visitaja.dto.leilao.LeilaoRequestDTO;
import br.com.visitaja.dto.leilao.LeilaoResponseDTO;
import br.com.visitaja.entity.Leilao;
import br.com.visitaja.enums.StatusLeilao;

public class LeilaoMapper {

    private LeilaoMapper() {}

    public static Leilao toEntity(LeilaoRequestDTO dto) {
        Leilao leilao = new Leilao();
        leilao.setTitulo(dto.titulo());
        leilao.setDescricao(dto.descricao());
        leilao.setStatus(StatusLeilao.ATIVO);
        return leilao;
    }

    public static void updateEntityFromDTO(LeilaoRequestDTO dto, Leilao leilao) {
        leilao.setTitulo(dto.titulo());
        leilao.setDescricao(dto.descricao());
    }

    public static LeilaoResponseDTO toResponseDTO(Leilao leilao) {
        return new LeilaoResponseDTO(
                leilao.getId(),
                leilao.getTitulo(),
                leilao.getDescricao(),
                leilao.getStatus()
        );
    }
}