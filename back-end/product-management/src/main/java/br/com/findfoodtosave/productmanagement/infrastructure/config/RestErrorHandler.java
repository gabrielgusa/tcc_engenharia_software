package br.com.findfoodtosave.productmanagement.infrastructure.config;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.findfoodtosave.productmanagement.domain.exception.BusinessException;
import br.com.findfoodtosave.productmanagement.infrastructure.exception.EntityNotFoundException;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    private static String INTERNAL_SERVER_ERRO_MESSAGE = "Internal Server Error.";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, 
        HttpHeaders headers, 
        HttpStatusCode status, 
        WebRequest request
    ) {

        var errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.add(Map.of("message", err.getDefaultMessage()));
        });
        
        var response = Map.of("errors", errors);

        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        return new ResponseEntity<>(Map.of("message", entityNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handlerBusinessException(BusinessException businessException) {
        return new ResponseEntity<>(Map.of("message", businessException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception exception) {
        return new ResponseEntity<>(Map.of("message", INTERNAL_SERVER_ERRO_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
