package br.com.visitaja.doc;

import br.com.visitaja.dto.leilao.LeilaoCompletoRequestDTO;
import br.com.visitaja.entity.Leilao;
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
        name = "Leilões",
        description = "Endpoints responsáveis pelo gerenciamento e consulta de leilões"
)
public interface LeilaoControllerDoc {

    @Operation(
            summary = "Lista leilões ativos (Público)",
            description = "Retorna uma lista contendo apenas os leilões que estão com o status ATIVO para visualização dos arrematantes."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Leilões ativos recuperados com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Leilao.class))
                    )
            )
    })
    ResponseEntity<List<Leilao>> listarLeiloesAtivos();


    @Operation(
            summary = "Cria um leilão completo com pátios e horários (Admin)",
            description = "Cadastra uma nova estrutura completa de leilão, incluindo seus pátios e horários vinculados, com base nos dados fornecidos."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Leilão completo criado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Leilao.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de requisição inválidos"
            )
    })
    ResponseEntity<Leilao> criarLeilaoCompleto(LeilaoCompletoRequestDTO dto);


    @Operation(
            summary = "Lista todos os leilões (Admin)",
            description = "Retorna uma lista com todos os leilões cadastrados no sistema, independentemente do status."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de leilões recuperada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Leilao.class))
                    )
            )
    })
    ResponseEntity<List<Leilao>> listarTodos();

}
