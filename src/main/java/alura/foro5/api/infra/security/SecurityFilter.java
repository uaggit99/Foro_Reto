package alura.foro5.api.infra.security;

import alura.foro5.api.domain.usuarios.UsuarioLogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioLogRepository usuarioLogRepository;
    @Autowired
    private TokenService tokenService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authHeader= request.getHeader("Authorization");

        if(authHeader!=null){
             var Token=authHeader.replace("Bearer ","");
            System.out.println(Token);
            var nombreUsuario=tokenService.getSubject(Token);
            if(nombreUsuario!= null){
                var usuario= usuarioLogRepository.findByLogin(nombreUsuario);
                var autentication= new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autentication);

            }

        }

        filterChain.doFilter(request, response);
    }
}
