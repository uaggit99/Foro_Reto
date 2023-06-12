package alura.foro5.api.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosregistroUsuario( @NotBlank String clave, @NotBlank String login, @NotBlank @Email String email) {
}
