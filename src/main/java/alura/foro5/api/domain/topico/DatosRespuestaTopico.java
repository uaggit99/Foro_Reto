package alura.foro5.api.domain.topico;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje,String Statuss, String fecha_creacion,DatosUsuario autor, DatosCurso curso) {
}
