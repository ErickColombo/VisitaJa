package br.com.visitaja.controller;

import br.com.visitaja.dto.agendamento.AgendamentoRequestDTO;
import br.com.visitaja.dto.agendamento.AgendamentoResponseDTO;
import br.com.visitaja.dto.horariovisita.HorarioVisitaResponseDTO;
import br.com.visitaja.dto.visitante.VisitanteResponseDTO;
import br.com.visitaja.entity.Agendamento;
import br.com.visitaja.entity.Visitante;
import br.com.visitaja.repository.AgendamentoRepository;
import br.com.visitaja.repository.VisitanteRepository;
import br.com.visitaja.service.AgendamentoService;
import br.com.visitaja.service.VisitanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AgendamentoRepository agendamentoRepository;
    private final VisitanteService visitanteService;
    private final VisitanteRepository visitanteRepository;

    // VISÃO DO ARREMATANTE (Público)
    @PostMapping("/publico/agendar")
    public ResponseEntity<AgendamentoResponseDTO> agendarVisita(@RequestBody @Valid AgendamentoRequestDTO dto) {

        Visitante visitante = visitanteRepository.findByCgc(dto.visitante().cgc())
                .orElseGet(() -> {
                    Visitante novoVisitante = new Visitante();
                    novoVisitante.setNome(dto.visitante().nome());
                    novoVisitante.setCgc(dto.visitante().cgc());
                    novoVisitante.setEmail(dto.visitante().email());
                    novoVisitante.setTelefone(dto.visitante().telefone());
                    return visitanteService.cadastrar(novoVisitante);
                });

        Agendamento agendamento = agendamentoService.realizarAgendamento(visitante.getId(), dto.horarioId());

        HorarioVisitaResponseDTO horarioDTO = new HorarioVisitaResponseDTO(
                agendamento.getHorario().getId(),
                agendamento.getHorario().getDataHora(),
                agendamento.getHorario().getVagasTotais(),
                agendamento.getHorario().getVagasDisponiveis(),
                agendamento.getHorario().getPatio().getId(),
                agendamento.getHorario().getPatio().getNome(),
                agendamento.getHorario().getPatio().getLeilao().getTitulo()
        );

        VisitanteResponseDTO visitanteDTO = new VisitanteResponseDTO(
                agendamento.getVisitante().getId(),
                agendamento.getVisitante().getNome(),
                agendamento.getVisitante().getCgc(),
                agendamento.getVisitante().getEmail(),
                agendamento.getVisitante().getTelefone()
        );

        AgendamentoResponseDTO response = new AgendamentoResponseDTO(
                agendamento.getId(),
                agendamento.getDataRegistro(),
                agendamento.getStatus(),
                horarioDTO,
                visitanteDTO
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // VISÃO DO ADMIN (Privado)
    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodosAgendamentos() {
        return ResponseEntity.ok(agendamentoRepository.findAll());
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarAgendamentoAdmin(@PathVariable Long id) {
        agendamentoService.cancelarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}