package br.com.visitaja.controller;

import br.com.visitaja.dto.horariovisita.HorarioVisitaRequestDTO;
import br.com.visitaja.entity.HorarioVisita;
import br.com.visitaja.service.HorarioVisitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorarioVisitaController {

    private final HorarioVisitaService horarioVisitaService;

    @GetMapping("/patio/{patioId}/disponiveis")
    public ResponseEntity<List<HorarioVisita>> listarDisponiveis(@PathVariable Long patioId) {
        List<HorarioVisita> horarios = horarioVisitaService.listarHorariosDisponiveisPorPatio(patioId);
        return ResponseEntity.ok(horarios);
    }

    @PostMapping
    public ResponseEntity<HorarioVisita> criarHorario(@RequestBody @Valid HorarioVisitaRequestDTO dto) {
        HorarioVisita novoHorario = horarioVisitaService.criarHorario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoHorario);
    }

    @GetMapping("/patio/{patioId}/todos")
    public ResponseEntity<List<HorarioVisita>> listarTodosDoPatio(@PathVariable Long patioId) {
        List<HorarioVisita> horarios = horarioVisitaService.listarTodosPorPatio(patioId);
        return ResponseEntity.ok(horarios);
    }
}