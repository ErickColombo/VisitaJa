package br.com.visitaja.doc;

import br.com.visitaja.dto.patio.PatioRequestDTO;
import br.com.visitaja.entity.Patio;
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
        name = "Pátios",
        description = "Endpoints responsáveis pelo gerenciamento e consulta de pátios vinculados aos leilões"
)
public interface PatioControllerDoc {

    @Operation(
            summary = "Lista pátios por leilão (Público)",
            description = "Retorna uma lista de pátios associados a um leilão específico com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pátios recuperados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Patio.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Leilão não encontrado"
            )
    })
    ResponseEntity<List<Patio>> listarPatiosDoLeilao(Long leilaoId);


    @Operation(
            summary = "Cria um novo pátio (Admin)",
            description = "Cadastra um novo pátio vinculado a um leilão existente com base nos dados fornecidos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Pátio criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patio.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Leilão associado não encontrado"
            )
    })
    ResponseEntity<Patio> criarPatio(PatioRequestDTO dto);

}
