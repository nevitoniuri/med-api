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
    public ResponseEntity<List<DadosErroValidacao>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(fieldErrors.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(ResourceDuplicatedException.class)
    public ResponseEntity<ResourceDuplicatedException> handleResourceDuplicatedException(ResourceDuplicatedException e) {
        return ResponseEntity.badRequest().body(e);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<InvalidDataException> handleInvalidDataException(InvalidDataException e) {
        return ResponseEntity.badRequest().body(e);
    }

}
