package alura.foro5.api.domain.modelo;

import alura.foro5.api.domain.topico.DatosUsuario;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String nombreU;
    private String email;


    public Usuario(DatosUsuario autor) {
        this.nombreU = autor.nombreU();
        this.email = autor.email();
    }



}