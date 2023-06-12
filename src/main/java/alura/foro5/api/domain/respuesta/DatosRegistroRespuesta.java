package alura.foro5.api.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(@NotBlank String mensaje, @NotNull Long topico, String fecha_respuesta, @NotBlank String autor, String solucion) {
}
