package alura.foro5.api.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(@NotBlank String login,@NotBlank String clave,@NotBlank @Email String email) {
}
