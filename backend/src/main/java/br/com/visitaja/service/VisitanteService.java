package br.com.visitaja.service;

import br.com.visitaja.entity.Visitante;
import br.com.visitaja.repository.VisitanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisitanteService {

    private final VisitanteRepository visitanteRepository;

    @Transactional
    public Visitante cadastrar(Visitante visitante) {
        if (visitanteRepository.findByCgc(visitante.getCgc()).isPresent()) {
            throw new IllegalStateException("Já existe um visitante cadastrado com este documento.");
        }
        return visitanteRepository.save(visitante);
    }

    public Visitante buscarPorId(Long id) {
        return visitanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visitante não encontrado."));
    }
}