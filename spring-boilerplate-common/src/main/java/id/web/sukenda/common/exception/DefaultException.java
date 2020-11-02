package id.web.sukenda.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DefaultException extends ResponseStatusException {

    public DefaultException(HttpStatus status, String message) {
        super(status, message);
    }
}
