package br.com.visitaja.doc;

import br.com.visitaja.dto.agendamento.AgendamentoRequestDTO;
import br.com.visitaja.dto.agendamento.AgendamentoResponseDTO;
import br.com.visitaja.entity.Agendamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Agendamentos",
        description = "Endpoints responsáveis pelo gerenciamento e realização de agendamentos de visitas"
)
public interface AgendamentoControllerDoc {

    @Operation(
            summary = "Realiza o agendamento de uma visita (Público)",
            description = "Permite que o arrematante realize o agendamento de uma visita a um pátio, cadastrando o visitante caso ele ainda não exista."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Agendamento realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgendamentoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos ou regras de negócio violadas"
            )
    })
    ResponseEntity<AgendamentoResponseDTO> agendarVisita(AgendamentoRequestDTO dto);


    @Operation(
            summary = "Lista todos os agendamentos (Admin)",
            description = "Retorna uma lista contendo todos os agendamentos cadastrados no sistema."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de agendamentos recuperada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Agendamento.class))
                    )
            )
    })
    ResponseEntity<List<Agendamento>> listarTodosAgendamentos();


    @Operation(
            summary = "Cancela um agendamento (Admin)",
            description = "Realiza o cancelamento de um agendamento específico com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Agendamento cancelado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Agendamento não encontrado"
            )
    })
    ResponseEntity<Void> cancelarAgendamentoAdmin(Long id);

}