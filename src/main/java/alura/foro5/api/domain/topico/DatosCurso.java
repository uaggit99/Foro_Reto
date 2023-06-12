package alura.foro5.api.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosCurso(@NotBlank String nombreC, @NotBlank String categoria) {
}
