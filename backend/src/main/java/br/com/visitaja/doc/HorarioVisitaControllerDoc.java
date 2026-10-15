package br.com.visitaja.doc;

import br.com.visitaja.dto.horariovisita.HorarioVisitaRequestDTO;
import br.com.visitaja.entity.HorarioVisita;
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
        name = "Horários de Visita",
        description = "Endpoints responsáveis pelo gerenciamento e consulta de horários de visita aos pátios"
)
public interface HorarioVisitaControllerDoc {

    @Operation(
            summary = "Lista horários disponíveis por pátio",
            description = "Retorna uma lista de horários de visita que possuem vagas disponíveis para um determinado pátio."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Horários disponíveis recuperados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = HorarioVisita.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pátio não encontrado"
            )
    })
    ResponseEntity<List<HorarioVisita>> listarDisponiveis(Long patioId);


    @Operation(
            summary = "Cria um novo horário de visita",
            description = "Cadastra um novo horário de visita vinculado a um pátio com base nos dados fornecidos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Horário criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = HorarioVisita.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pátio associado não encontrado"
            )
    })
    ResponseEntity<HorarioVisita> criarHorario(HorarioVisitaRequestDTO dto);


    @Operation(
            summary = "Lista todos os horários de um pátio",
            description = "Retorna todos os horários cadastrados (disponíveis e indisponíveis) para um pátio específico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de horários recuperada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = HorarioVisita.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pátio não encontrado"
            )
    })
    ResponseEntity<List<HorarioVisita>> listarTodosDoPatio(Long patioId);

}
