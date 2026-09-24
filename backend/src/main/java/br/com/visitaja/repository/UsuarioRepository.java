package br.com.visitaja.repository;

import br.com.visitaja.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Usado pelo Spring Security para validar o login
    Optional<Usuario> findByUser(String user);
}