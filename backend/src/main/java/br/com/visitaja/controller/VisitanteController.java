package br.com.visitaja.controller;

import br.com.visitaja.entity.Visitante;
import br.com.visitaja.service.VisitanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitantes")
@RequiredArgsConstructor
public class VisitanteController {

    private final VisitanteService visitanteService;

    @PostMapping
    public ResponseEntity<Visitante> cadastrar(@RequestBody Visitante visitante) {
        Visitante novoVisitante = visitanteService.cadastrar(visitante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVisitante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitante> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(visitanteService.buscarPorId(id));
    }
}