package br.com.visitaja.controller;

import br.com.visitaja.entity.Agendamento;
import br.com.visitaja.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    // Record para mapear o JSON que vem do frontend
    public record AgendamentoRequest(Long visitanteId, Long horarioId) {}

    @PostMapping
    public ResponseEntity<Agendamento> agendar(@RequestBody AgendamentoRequest request) {
        Agendamento agendamento = agendamentoService.realizarAgendamento(
                request.visitanteId(),
                request.horarioId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}