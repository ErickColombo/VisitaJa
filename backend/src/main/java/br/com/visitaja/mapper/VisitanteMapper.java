package br.com.visitaja.mapper;

import br.com.visitaja.dto.visitante.VisitanteRequestDTO;
import br.com.visitaja.dto.visitante.VisitanteResponseDTO;
import br.com.visitaja.entity.Visitante;

public class VisitanteMapper {

    private VisitanteMapper() {}

    public static Visitante toEntity(VisitanteRequestDTO dto) {
        Visitante visitante = new Visitante();
        visitante.setNome(dto.nome());
        visitante.setCgc(dto.cgc());
        visitante.setEmail(dto.email());
        visitante.setTelefone(dto.telefone());
        return visitante;
    }

    public static void updateEntityFromDTO(VisitanteRequestDTO dto, Visitante visitante) {
        visitante.setNome(dto.nome());
        visitante.setEmail(dto.email());
        visitante.setTelefone(dto.telefone());
        // cgc não é atualizado: é o identificador natural usado para
        // localizar o visitante (get-or-create), não deve mudar depois de criado.
    }

    public static VisitanteResponseDTO toResponseDTO(Visitante visitante) {
        return new VisitanteResponseDTO(
                visitante.getId(),
                visitante.getNome(),
                visitante.getCgc(),
                visitante.getEmail(),
                visitante.getTelefone()
        );
    }
}