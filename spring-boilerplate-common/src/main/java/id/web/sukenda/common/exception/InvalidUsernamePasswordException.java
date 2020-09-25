package id.web.sukenda.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUsernamePasswordException extends ResponseStatusException {

    public InvalidUsernamePasswordException(HttpStatus status, String message) {
        super(status, message);
    }
}
