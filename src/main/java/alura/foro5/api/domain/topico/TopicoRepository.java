package alura.foro5.api.domain.topico;

import alura.foro5.api.domain.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByActivoTrue(Pageable paginacion);

    Page <Topico> findByActivoFalse(Pageable paginacion);
}
