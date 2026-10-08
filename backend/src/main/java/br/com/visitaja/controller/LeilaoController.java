package br.com.visitaja.controller;

import br.com.visitaja.entity.Leilao;
import br.com.visitaja.enums.StatusLeilao;
import br.com.visitaja.repository.LeilaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leiloes")
@RequiredArgsConstructor
public class LeilaoController {

    private final LeilaoRepository leilaoRepository;

    // VISÃO DO ARREMATANTE (Público)
    @GetMapping("/ativos")
    public ResponseEntity<List<Leilao>> listarLeiloesAtivos() {
        // Filtra os leilões para mostrar apenas os ativos na Home
        List<Leilao> ativos = leilaoRepository.findAll().stream()
                .filter(l -> l.getStatus() == StatusLeilao.ATIVO)
                .toList();
        return ResponseEntity.ok(ativos);
    }

    // VISÃO DO ADMIN (Privado)
    @PostMapping
    public ResponseEntity<Leilao> criarLeilao(@RequestBody Leilao leilao) {
        Leilao novoLeilao = leilaoRepository.save(leilao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLeilao);
    }

    @GetMapping
    public ResponseEntity<List<Leilao>> listarTodos() {
        return ResponseEntity.ok(leilaoRepository.findAll());
    }
}