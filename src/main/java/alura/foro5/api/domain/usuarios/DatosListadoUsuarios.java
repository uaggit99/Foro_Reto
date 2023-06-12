package alura.foro5.api.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosListadoUsuarios(Long id,String clave, String login, String email) {

    public DatosListadoUsuarios(UsuarioLog usuarioLog){
        this(usuarioLog.getId(),usuarioLog.getClave(),usuarioLog.getLogin(), usuarioLog.getEmail());
    }

}
