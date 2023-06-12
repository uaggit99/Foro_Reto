package alura.foro5.api.domain.topico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(@NotBlank String titulo,
                                  @NotBlank
                                  String mensaje,
                                  //@NotBlank
                                  //@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$")
                                  String fecha_creacion,
                                  //@NotNull
                                  StatusTopico  Statuss ,
                                  @NotNull
                                  @Valid
                                  DatosUsuario autor,
                                  @NotNull
                                  @Valid
                                  DatosCurso curso) {
}
