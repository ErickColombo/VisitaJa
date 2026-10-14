package br.com.visitaja.config;

import br.com.visitaja.entity.Usuario;
import br.com.visitaja.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminDataLoaderTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateAdminOnlyOnce() {
        Usuario existingAdmin = new Usuario();
        when(usuarioRepository.findByUsername("admin"))
                .thenReturn(Optional.empty(), Optional.of(existingAdmin));
        when(passwordEncoder.encode("secret")).thenReturn("encoded-secret");

        AdminDataLoader loader = new AdminDataLoader(
                usuarioRepository,
                passwordEncoder,
                "admin",
                "secret");

        loader.run();
        loader.run();

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);
        verify(usuarioRepository).save(captor.capture());
        assertEquals("admin", captor.getValue().getUsername());
        assertEquals("encoded-secret", captor.getValue().getSenha());
    }

    @Test
    void shouldNotCreateAdminWithoutCredentials() {
        AdminDataLoader loader = new AdminDataLoader(
                usuarioRepository,
                passwordEncoder,
                "",
                "");

        loader.run();

        verify(usuarioRepository, never()).findByUsername(any());
        verify(usuarioRepository, never()).save(any());
    }
}
