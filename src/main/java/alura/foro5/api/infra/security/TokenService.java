package alura.foro5.api.infra.security;

import alura.foro5.api.domain.usuarios.UsuarioLog;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("{api.security.secret}")
    private String apiSecret;
    public String generarToken(UsuarioLog usuarioLog ){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return  JWT.create()
                    .withIssuer("alura foro5")
                    .withSubject(usuarioLog.getLogin())
                    .withClaim("id",usuarioLog.getId())
                    .withExpiresAt(tiempoExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
           throw  new RuntimeException();
        }
    }

    public Instant tiempoExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));

    }
    public String getSubject(String token) {
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    // specify an specific claim validations
                    .withIssuer("alura foro5")
                    // reusable verifier instance
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
             System.out.println(exception.toString());
        }
        if(verifier.getSubject()== null){
            throw  new RuntimeException("verifier Invalido");
        }
        return verifier.getSubject();

    }
}

