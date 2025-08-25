package fr.diginamic.demospring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler({ ExceptionElement.class})
    public ResponseEntity<String> handleException(ExceptionElement e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
