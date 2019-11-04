package pw.backend.lab.backlab.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyUserNotFoundException extends RuntimeException {
    public MyUserNotFoundException(String message) {
        super(message);
    }

    public MyUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}