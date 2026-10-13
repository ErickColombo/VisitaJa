package br.com.visitaja.config;

import br.com.visitaja.entity.Usuario;
import br.com.visitaja.enums.Role;
import br.com.visitaja.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminUsername;
    private final String adminPassword;

    public AdminDataLoader(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            @Value("${ADMIN_USERNAME:}") String adminUsername,
            @Value("${ADMIN_PASSWORD:}") String adminPassword) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    @Override
    public void run(String... args) {
        if (adminUsername.isBlank() || adminPassword.isBlank()) {
            return;
        }

        if (usuarioRepository.findByUsername(adminUsername).isPresent()) {
            return;
        }

        Usuario admin = new Usuario();
        admin.setUsername(adminUsername);
        admin.setSenha(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.ADMIN);
        usuarioRepository.save(admin);
    }
}
