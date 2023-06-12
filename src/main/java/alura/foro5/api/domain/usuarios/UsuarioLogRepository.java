package alura.foro5.api.domain.usuarios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioLogRepository extends JpaRepository<UsuarioLog, Long> {
    Page<UsuarioLog> findByActivoTrue(Pageable paginacion);

    UserDetails findByLogin(String username);


}
