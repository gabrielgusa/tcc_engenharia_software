package br.com.findfoodtosave.usermanagement.infrastructure.exception;

public class UserAlreadyExistsException extends RuntimeException {
    
    public UserAlreadyExistsException(String message){
        super(message);
    }
    
}
