package alura.foro5.api.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;


@Table(name="usuarioslog")
@Entity(name="UsuarioLog")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioLog  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String login;
    private String clave;
    private String email;
    private Boolean activo;
    private static String bcript;
    private static String valor;




    public UsuarioLog(DatosregistroUsuario datosregistroUsuario){
        valor=datosregistroUsuario.clave();
        convertirBcrypt(valor);
        this.clave= bcript;
        this.login = datosregistroUsuario.login();
        this.email=datosregistroUsuario.email();
        this.activo=true;
    }

    @Override
    public String toString() {
        return "UsuarioLog{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", clave='" + clave + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void actualizardatos(DatosActualizarUsuario datosActualizarUsuario) {
        if(datosActualizarUsuario.login()!=null){
            this.login=datosActualizarUsuario.login();
        }
        if(datosActualizarUsuario.clave()!=null){
            convertirBcrypt(datosActualizarUsuario.clave());
            this.clave=bcript;
        }
        if(datosActualizarUsuario.email()!=null){
            this.email= datosActualizarUsuario.email();
        }
    }
    public void convertirBcrypt(String Valor){
        String password =Valor;
        bcript= BCrypt.hashpw(password, BCrypt.gensalt(10));
           }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public void desactivarUsuario() {
        this.activo=false;
    }
}
