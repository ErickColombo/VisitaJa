package br.com.visitaja.dto.usuario;

import br.com.visitaja.enums.Role;

public record UsuarioResponseDTO(

        Long id,
        String user,
        Role role

) {}