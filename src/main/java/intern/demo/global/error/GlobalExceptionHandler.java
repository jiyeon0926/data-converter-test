package intern.demo.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<String> handleResponseStatusExceptions(ResponseStatusException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(e.getReason());
    }
}