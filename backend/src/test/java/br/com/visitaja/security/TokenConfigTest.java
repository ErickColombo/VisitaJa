package br.com.visitaja.security;

import br.com.visitaja.entity.Usuario;
import br.com.visitaja.enums.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenConfigTest {

    private final TokenConfig tokenConfig = new TokenConfig("test-secret", 3600);

    @Test
    void shouldGenerateAndValidateToken() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("admin");
        usuario.setRole(Role.ADMIN);

        String token = tokenConfig.generateToken(usuario);

        JWTUserData userData = tokenConfig.validateToken(token).orElseThrow();

        assertEquals(1L, userData.userId());
        assertEquals("admin", userData.username());
        assertEquals(Role.ADMIN, userData.role());
    }

    @Test
    void shouldRejectInvalidToken() {
        assertTrue(tokenConfig.validateToken("invalid-token").isEmpty());
    }
}
