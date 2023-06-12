package alura.foro5.api.domain.respuesta;


import alura.foro5.api.domain.topico.StatusTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name="respuestas")
@Entity(name="Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private Long topico;
    private String fecha_respuesta ;
    private String autor;
    private String solucion;
    private static String  datetime;
    private static String estado = String.valueOf(StatusTopico.NO_SOLUCIONADO);
    private  boolean activo;

    public Respuesta (DatosRegistroRespuesta datosRegistroRespuesta){

        fechaRespuesta();
        this.mensaje= datosRegistroRespuesta.mensaje();
        this.topico=datosRegistroRespuesta.topico();
        this.fecha_respuesta= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(LocalDateTime.now());;
        this.autor=datosRegistroRespuesta.autor();
        this.solucion=estado;
        this.activo=true;

    }

    public  void desactivarRespuesta() {
        this.activo=false;
    }

    private void fechaRespuesta() {
       // datetime =
    }


    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
        if(datosActualizarRespuesta.mensaje()!=null){
            this.mensaje=datosActualizarRespuesta.mensaje();
        }
        if(datosActualizarRespuesta.solucion()!= null){
            this.solucion=datosActualizarRespuesta.solucion();

        }

    }
}
