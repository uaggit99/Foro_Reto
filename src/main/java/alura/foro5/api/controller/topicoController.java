package alura.foro5.api.controller;


import alura.foro5.api.domain.modelo.Topico;
import alura.foro5.api.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class topicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registroTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        //System.out.println(datosRegistroTopico);
        Topico topico=topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico=new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatuss().toString(),topico.getFecha_creacion(), new DatosUsuario(topico.getAutor().getNombreU(),topico.getAutor().getEmail()),
                new DatosCurso(topico.getCurso().getNombreC(),topico.getCurso().getCategoria()));

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size=5,sort="titulo") Pageable paginacion){
        //return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new)) ;

    }



    @PutMapping
    @Transactional
    public ResponseEntity modificarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizardatos(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatuss().toString(),topico.getFecha_creacion(), new DatosUsuario(topico.getAutor().getNombreU(),topico.getAutor().getEmail()),
                new DatosCurso(topico.getCurso().getNombreC(),topico.getCurso().getCategoria())));

    }
    /*@DeleteMapping("/{id}")
    @Transactional
    public void deteteDatos(@PathVariable Long id){
        Topico topico=topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);

    }*/

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deteteLogico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> retornoDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var DatosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getStatuss().toString(),topico.getFecha_creacion(), new DatosUsuario(topico.getAutor().getNombreU(),topico.getAutor().getEmail()),
                new DatosCurso(topico.getCurso().getNombreC(),topico.getCurso().getCategoria()));
        return ResponseEntity.ok(DatosTopico);
    }
}