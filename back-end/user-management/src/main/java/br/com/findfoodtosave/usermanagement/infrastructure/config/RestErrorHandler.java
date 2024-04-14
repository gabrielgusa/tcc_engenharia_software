package br.com.findfoodtosave.usermanagement.infrastructure.config;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.findfoodtosave.usermanagement.infrastructure.exception.UserAlreadyExistsException;
import br.com.findfoodtosave.usermanagement.infrastructure.exception.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    private static String INTERNAL_SERVER_ERRO_MESSAGE = "Internal Server Error.";
    private static String UNAUTHORIZED_MESSAGE = "User unauthorized.";
    private static String INVALID_CREDENTIALS_MESSAGE = "Usuário ou Senha invalido.";

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

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handlerUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException) {
        return new ResponseEntity<>(Map.of("message", userAlreadyExistsException.getMessage()), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlerUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(Map.of("message", userNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Object> handlerInternalAuthenticationServiceException(InternalAuthenticationServiceException internalAuthenticationServiceException) {
        if(internalAuthenticationServiceException.getCause().getClass().equals(UserNotFoundException.class)){
            return this.handlerUserNotFoundException((UserNotFoundException) internalAuthenticationServiceException.getCause());
        }
        return this.handleSecurityException(internalAuthenticationServiceException);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handlerBadCredentialsException(BadCredentialsException badCredentialsException) {
        return new ResponseEntity<>(Map.of("message", INVALID_CREDENTIALS_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handlerException(Exception exception) {
        return new ResponseEntity<>(Map.of("message", INTERNAL_SERVER_ERRO_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
        AccountStatusException.class,
        AccessDeniedException.class,
        SignatureException.class,
        ExpiredJwtException.class,
        MalformedJwtException.class
    })
    public ResponseEntity<Object> handleSecurityException(Exception exception) {
        return new ResponseEntity<>(Map.of("message", UNAUTHORIZED_MESSAGE), HttpStatus.UNAUTHORIZED);
    }

}
