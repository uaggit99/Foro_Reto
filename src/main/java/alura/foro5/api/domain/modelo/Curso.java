package alura.foro5.api.domain.modelo;

import alura.foro5.api.domain.topico.DatosCurso;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    private String nombreC;
    private String categoria;

    public Curso(DatosCurso curso) {
        this.nombreC= curso.nombreC();
        this.categoria= curso.categoria();
    }

    public Curso actualizarDatosC(DatosCurso curso) {
        if(curso.nombreC()!=null){
            this.nombreC= curso.nombreC();

        }
        if(curso.categoria()!=null){
            this.categoria= curso.categoria();

        }

        return this;

    }


}