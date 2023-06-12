package alura.foro5.api.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<UsuarioLog, Long> {

}
