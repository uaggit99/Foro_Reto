package alura.foro5.api.controller;

import alura.foro5.api.domain.respuesta.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/respuesta")
public class respuestaController {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<DatosRespuesta> registroRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRegistroRespuesta, UriComponentsBuilder uriComponentsBuilder) {
        //System.out.println(datosRegistroTopico);
        Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistroRespuesta));
        DatosRespuesta datosRespuesta = new DatosRespuesta(respuesta.getId(), respuesta.getMensaje(),
                respuesta.getTopico(), respuesta.getFecha_respuesta(), respuesta.getAutor(), respuesta.getSolucion());

        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deteteLogicoRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuesta.desactivarRespuesta();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoRespuestas> retornoDatosTopico(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        var DatosRespuesta = new DatosListadoRespuestas(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico(), respuesta.getFecha_respuesta(),
                respuesta.getAutor(), respuesta.getSolucion());
        return ResponseEntity.ok(DatosRespuesta);

    }

    @PutMapping
    @Transactional
    public ResponseEntity modificarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuesta(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico(),respuesta.getFecha_respuesta().toString(), respuesta.getAutor(), respuesta.getSolucion()));

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoRespuestas>> listadoRespuestas(@PageableDefault(size=5,sort="autor") Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findByActivoTrue(paginacion).map(DatosListadoRespuestas::new));

    }
}