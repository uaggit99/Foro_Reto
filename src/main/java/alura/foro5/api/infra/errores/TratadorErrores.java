package alura.foro5.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class TratadorErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return ResponseEntity.notFound().build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream().map(datosErrores::new).toList();
        return ResponseEntity.badRequest().body(errores);

    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity errorEmail(SQLIntegrityConstraintViolationException e) {
        String message= "Correo Ya esta Registrado ";

        return  new ResponseEntity(message, HttpStatus.IM_USED);

    }

    public record datosErrores(String campo, String error) {
        public datosErrores(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }


    }
}
