package br.com.visitaja.controller;

import br.com.visitaja.dto.leilao.LeilaoCompletoRequestDTO;
import br.com.visitaja.entity.Leilao;
import br.com.visitaja.enums.StatusLeilao;
import br.com.visitaja.repository.LeilaoRepository;
import br.com.visitaja.service.LeilaoService;
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
    private final LeilaoService leilaoService;

    // VISÃO DO ARREMATANTE (Público)
    @GetMapping("/ativos")
    public ResponseEntity<List<Leilao>> listarLeiloesAtivos() {
        List<Leilao> ativos = leilaoRepository.findAll().stream()
                .filter(l -> l.getStatus() == StatusLeilao.ATIVO)
                .toList();
        return ResponseEntity.ok(ativos);
    }


    // VISÃO DO ADMIN (Privado)
    @PostMapping("/completo")
    public ResponseEntity<Leilao> criarLeilaoCompleto(@RequestBody LeilaoCompletoRequestDTO dto) {
        Leilao novo = leilaoService.criarLeilaoCompleto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @GetMapping
    public ResponseEntity<List<Leilao>> listarTodos() {
        return ResponseEntity.ok(leilaoRepository.findAll());
    }
}