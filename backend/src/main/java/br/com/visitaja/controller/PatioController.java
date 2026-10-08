package br.com.visitaja.controller;

import br.com.visitaja.dto.patio.PatioRequestDTO;
import br.com.visitaja.entity.Leilao;
import br.com.visitaja.entity.Patio;
import br.com.visitaja.repository.LeilaoRepository;
import br.com.visitaja.repository.PatioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patios")
@RequiredArgsConstructor
public class PatioController {

    private final PatioRepository patioRepository;
    private final LeilaoRepository leilaoRepository;

    // VISÃO DO ARREMATANTE (Público)
    @GetMapping("/leilao/{leilaoId}")
    public ResponseEntity<List<Patio>> listarPatiosDoLeilao(@PathVariable Long leilaoId) {
        List<Patio> patios = patioRepository.findByLeilaoId(leilaoId);
        return ResponseEntity.ok(patios);
    }

    // VISÃO DO ADMIN (Privado)
    @PostMapping
    public ResponseEntity<Patio> criarPatio(@RequestBody @Valid PatioRequestDTO dto) {
        Leilao leilao = leilaoRepository.findById(dto.leilaoId())
                .orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado com o ID fornecido."));

        Patio patio = new Patio();
        patio.setNome(dto.nome());
        patio.setEndereco(dto.endereco());
        patio.setLeilao(leilao);

        Patio novoPatio = patioRepository.save(patio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPatio);
    }
}