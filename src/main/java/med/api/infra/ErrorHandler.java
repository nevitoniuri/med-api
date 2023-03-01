package med.api.infra;

import med.api.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Throwable> handle404(ResourceNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handle400(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(DadosErroValidacao::new).toList());
    }

}
