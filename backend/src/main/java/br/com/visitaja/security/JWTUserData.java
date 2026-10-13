package br.com.visitaja.security;

import br.com.visitaja.enums.Role;
import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String username, Role role) {
}
