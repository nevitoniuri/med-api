package med.voll.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceDuplicatedException extends RuntimeException {

    public ResourceDuplicatedException(String message) {
        super(message);
    }
}
