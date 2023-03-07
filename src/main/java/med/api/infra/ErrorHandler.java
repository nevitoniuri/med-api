package med.api.infra;

import med.api.exception.InvalidDataException;
import med.api.exception.ResourceDuplicatedException;
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
    public ResponseEntity<ResourceNotFoundException> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handle400(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ResourceDuplicatedException.class)
    public ResponseEntity<Throwable> handle409(ResourceDuplicatedException e) {
        return ResponseEntity.status(409).build();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<Throwable> handleInvalidDataException(InvalidDataException e) {
        return ResponseEntity.badRequest().build();
    }

}
