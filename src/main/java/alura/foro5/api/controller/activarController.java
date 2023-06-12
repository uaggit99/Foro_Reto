package alura.foro5.api.controller;

import alura.foro5.api.domain.modelo.Topico;
import alura.foro5.api.domain.topico.DatosListadoTopico;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import alura.foro5.api.domain.topico.TopicoRepository;

@RestController
@RequestMapping("/activar")
public class activarController {
    @Autowired
     private TopicoRepository topicoRepository;
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicosdesactivados(@PageableDefault(size=5,sort="titulo") Pageable paginacion){
        //return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
        return ResponseEntity.ok(topicoRepository.findByActivoFalse(paginacion).map(DatosListadoTopico::new)) ;

    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deteteLogico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.activarTopico();
        return ResponseEntity.noContent().build();
    }

}
