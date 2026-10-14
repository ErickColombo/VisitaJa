package br.com.visitaja.security;

import br.com.visitaja.entity.Usuario;
import br.com.visitaja.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenConfig {

    private final Algorithm algorithm;
    private final long expirationSeconds;

    public TokenConfig(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration-seconds:86400}") long expirationSeconds) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.expirationSeconds = expirationSeconds;
    }

    public String generateToken(Usuario usuario) {
        return JWT.create()
                .withClaim("userId", usuario.getId())
                .withClaim("role", usuario.getRole().name())
                .withSubject(usuario.getUsername())
                .withExpiresAt(Instant.now().plusSeconds(expirationSeconds))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            DecodedJWT decoded = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData.builder()
                    .username(decoded.getSubject())
                    .userId(decoded.getClaim("userId").asLong())
                    .role(Role.valueOf(decoded.getClaim("role").asString()))
                    .build());
        } catch (JWTVerificationException | IllegalArgumentException exception) {
            return Optional.empty();
        }
    }
}
