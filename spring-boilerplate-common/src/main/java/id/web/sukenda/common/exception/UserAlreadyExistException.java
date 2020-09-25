package id.web.sukenda.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistException extends ResponseStatusException {

    public UserAlreadyExistException(HttpStatus status, String message) {
        super(status, message);
    }
}
