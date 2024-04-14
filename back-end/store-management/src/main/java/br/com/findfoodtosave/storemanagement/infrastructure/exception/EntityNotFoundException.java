package br.com.findfoodtosave.storemanagement.infrastructure.exception;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }

}
