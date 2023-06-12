package alura.foro5.api.domain.modelo;

import alura.foro5.api.domain.topico.DatosActualizarTopico;
import alura.foro5.api.domain.topico.DatosRegistroTopico;
import alura.foro5.api.domain.topico.StatusTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String fecha_creacion;
    @Enumerated(EnumType.STRING)
    private StatusTopico Statuss;
    @Embedded
    private Usuario autor;
    @Embedded
    private Curso curso;
    private boolean activo;
    private static String dateTime;


    public Topico(DatosRegistroTopico datosRegistroTopico) {
        fechaHora();
        this.activo = true;
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha_creacion = dateTime;
        this.Statuss = StatusTopico.NO_RESPONDIDO;
        this.autor = new Usuario(datosRegistroTopico.autor());
        this.curso = new Curso(datosRegistroTopico.curso());

    }

    private void  fechaHora() {
        dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(LocalDateTime.now());
        //System.out.println(dateTime);

    }
    public void actualizardatos(DatosActualizarTopico datosActualizarTopico) {
        if(datosActualizarTopico.titulo()!=null){
            this.titulo=datosActualizarTopico.titulo();

        }
        if(datosActualizarTopico.mensaje()!=null){
            this.mensaje=datosActualizarTopico.mensaje();

        }
        if(datosActualizarTopico.Statuss()!=null){
            this.Statuss=StatusTopico.valueOf(datosActualizarTopico.Statuss());

        }
        if(datosActualizarTopico.curso()!=null){
            this.curso=curso.actualizarDatosC(datosActualizarTopico.curso());

        }





    }

    public void desactivarTopico() {
        this.activo=false;
    }

    public void activarTopico() {
        this.activo=true;
    }
}
