package br.com.findfoodtosave.productmanagement.infrastructure.exception;

public class EntityNotFoundException extends RuntimeException {
    
    public EntityNotFoundException(String message) {
        super(message);
    }

}
