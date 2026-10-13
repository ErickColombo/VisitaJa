package br.com.visitaja.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;

    public SecurityFilter(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring("Bearer ".length());
            Optional<JWTUserData> optionalUser = tokenConfig.validateToken(token);

            if (optionalUser.isPresent()) {
                JWTUserData userData = optionalUser.get();
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userData.role().name()));
                var authenticationToken = new UsernamePasswordAuthenticationToken(
                        userData,
                        null,
                        authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
