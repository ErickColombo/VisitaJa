package br.com.visitaja.doc;

import br.com.visitaja.dto.request.LoginRequest;
import br.com.visitaja.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Auth",
        description = "Endpoints responsáveis pela autenticação de usuários"
)
public interface AuthControllerDoc {

    @Operation(
            summary = "Realiza o login do usuário",
            description = "Autentica o usuário utilizando credenciais (username e password) e retorna um token JWT."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados de login inválidos"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidas ou usuário não autorizado"
            )
    })
    ResponseEntity<LoginResponse> login(LoginRequest request);

}