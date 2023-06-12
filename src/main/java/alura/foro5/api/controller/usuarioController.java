package alura.foro5.api.controller;

import alura.foro5.api.domain.modelo.Topico;
import alura.foro5.api.domain.topico.*;
import alura.foro5.api.domain.usuarios.*;
import alura.foro5.api.infra.security.MailService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
;import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class usuarioController {
    @Autowired
    private UsuarioLogRepository usuarioRepository;
    @Autowired
    private MailService mailService;
    private String  clave;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrousuario(@RequestBody @Valid DatosregistroUsuario datosregistroUsuario, UriComponentsBuilder uriComponentsBuilder){
         clave= datosregistroUsuario.clave();
         UsuarioLog usuarioLog = usuarioRepository.save(new UsuarioLog(datosregistroUsuario));

        if(usuarioLog!=null){
            mailService.envioEmail(usuarioLog.getEmail(),usuarioLog.getLogin(),clave);
        }
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuarioLog.getId(),usuarioLog.getLogin(),usuarioLog.getClave(), usuarioLog.getEmail());
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuarioLog.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuarios>> listadoUsuarios(@PageableDefault(size=5,sort="login") Pageable paginacion){
        //return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuarios::new)) ;

    }
    @PutMapping
    @Transactional
    public ResponseEntity modificarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
         var valor= datosActualizarUsuario.clave();
         if(datosActualizarUsuario.clave()!=null){
             clave= datosActualizarUsuario.clave();
         }else{
             clave="contraseña no alterada";
         }

        UsuarioLog usuarioLog = usuarioRepository.getReferenceById(datosActualizarUsuario.id());

        usuarioLog.actualizardatos(datosActualizarUsuario);

        if(usuarioLog!=null){

            mailService.envioEmail(usuarioLog.getEmail(),usuarioLog.getLogin(),clave);
         }

        return ResponseEntity.ok( new DatosRespuestaUsuario(usuarioLog.getId(),usuarioLog.getLogin(),
                usuarioLog.getClave(),usuarioLog.getEmail()));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deteteLogico(@PathVariable Long id) {
        UsuarioLog usuarioLog = usuarioRepository.getReferenceById(id);
        usuarioLog.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatoRespuestaUsuario> retornoDatosUsuario(@PathVariable Long id) {
        UsuarioLog usuarioLog = usuarioRepository.getReferenceById(id);
        var DatosUsuario= new DatoRespuestaUsuario(usuarioLog.getLogin(),usuarioLog.getEmail());
        return ResponseEntity.ok(DatosUsuario);
    }



}
