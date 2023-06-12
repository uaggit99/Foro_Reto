package alura.foro5.api.domain.respuesta;

public record DatosListadoRespuestas(Long id,String mensaje,Long topico, String fecha_respuesta,String autor, String solucion) {

    public DatosListadoRespuestas(Respuesta respuesta){
        this(respuesta.getId(),respuesta.getMensaje(),respuesta.getTopico(),respuesta.getFecha_respuesta(),respuesta.getAutor(),respuesta.getSolucion());
    }
}
