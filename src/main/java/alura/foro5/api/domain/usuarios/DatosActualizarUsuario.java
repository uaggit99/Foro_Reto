package alura.foro5.api.domain.usuarios;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id, String clave, String login, String email) {
}
