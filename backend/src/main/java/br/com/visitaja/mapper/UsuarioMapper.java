package br.com.visitaja.mapper;

import br.com.visitaja.dto.usuario.UsuarioRequestDTO;
import br.com.visitaja.dto.usuario.UsuarioResponseDTO;
import br.com.visitaja.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static Usuario toEntity(UsuarioRequestDTO dto, String senha) {
        Usuario usuario = new Usuario();
        usuario.setUser(dto.user());
        usuario.setSenha(senha);
        usuario.setRole(dto.role());
        return usuario;
    }

    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getUser(),
                usuario.getRole()
        );
    }
}