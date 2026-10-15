package br.com.visitaja.controller;

import br.com.visitaja.doc.AuthControllerDoc;
import br.com.visitaja.dto.request.LoginRequest;
import br.com.visitaja.dto.response.LoginResponse;
import br.com.visitaja.entity.Usuario;
import br.com.visitaja.security.TokenConfig;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDoc {

    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken userAndPass =
                new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(usuario);
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
