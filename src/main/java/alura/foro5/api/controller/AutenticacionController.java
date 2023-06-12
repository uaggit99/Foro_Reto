package alura.foro5.api.controller;

import alura.foro5.api.domain.usuarios.DatosAutenticacionUsuario;
import alura.foro5.api.domain.usuarios.UsuarioLog;
import alura.foro5.api.infra.security.DatosJWTtoken;
import alura.foro5.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired private TokenService tokenService;
     @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){

         Authentication authtoken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                 datosAutenticacionUsuario.clave());
         var usuarioAutenticado=authenticationManager.authenticate(authtoken);
         var JWTtoken= tokenService.generarToken((UsuarioLog) usuarioAutenticado.getPrincipal());
         return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));


    }

}
