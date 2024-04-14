package br.com.findfoodtosave.usermanagement.infrastructure.exception;

public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
    
}
