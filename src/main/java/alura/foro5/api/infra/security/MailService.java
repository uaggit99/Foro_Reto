package alura.foro5.api.infra.security;

import alura.foro5.api.domain.usuarios.UsuarioLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public void envioEmail(String email, String login , String clave){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ace77ey@gmail.com");
        message.setTo(email);
        message.setSubject("Datos en el foro");
        message.setText( "!! los datos actuales son !!  "+ "su login es :  "+ login
                +" su clave es  : "+ clave);

        mailSender.send(message);

    }



}
