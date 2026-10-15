package br.com.visitaja.doc;

import br.com.visitaja.entity.Visitante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Visitantes",
        description = "Endpoints responsáveis pelo cadastro e consulta de visitantes"
)
public interface VisitanteControllerDoc {

    @Operation(
            summary = "Cadastra um novo visitante",
            description = "Realiza o cadastro de um novo visitante no sistema com base nos dados fornecidos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Visitante cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Visitante.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos"
            )
    })
    ResponseEntity<Visitante> cadastrar(Visitante visitante);


    @Operation(
            summary = "Busca um visitante por ID",
            description = "Retorna os detalhes de um visitante específico com base no ID fornecido."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Visitante encontrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Visitante.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Visitante não encontrado"
            )
    })
    ResponseEntity<Visitante> buscar(Long id);

}