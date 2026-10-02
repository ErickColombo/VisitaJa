package br.com.visitaja.mapper;

import br.com.visitaja.dto.patio.PatioRequestDTO;
import br.com.visitaja.dto.patio.PatioResponseDTO;
import br.com.visitaja.entity.Leilao;
import br.com.visitaja.entity.Patio;

public class PatioMapper {

    private PatioMapper() {}

    /**
     * O Leilao já deve ter sido buscado no banco (via LeilaoRepository)
     * antes de chamar este método — o mapper não acessa repositórios.
     */
    public static Patio toEntity(PatioRequestDTO dto, Leilao leilao) {
        Patio patio = new Patio();
        patio.setNome(dto.nome());
        patio.setEndereco(dto.endereco());
        patio.setLeilao(leilao);
        return patio;
    }

    public static void updateEntityFromDTO(PatioRequestDTO dto, Patio patio, Leilao leilao) {
        patio.setNome(dto.nome());
        patio.setEndereco(dto.endereco());
        patio.setLeilao(leilao);
    }

    public static PatioResponseDTO toResponseDTO(Patio patio) {
        return new PatioResponseDTO(
                patio.getId(),
                patio.getNome(),
                patio.getEndereco(),
                patio.getLeilao().getId(),
                patio.getLeilao().getTitulo()
        );
    }
}
