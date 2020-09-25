package id.web.sukenda.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DataAlreadyExistException extends ResponseStatusException {

    public DataAlreadyExistException(HttpStatus status, String message) {
        super(status, message);
    }
}
