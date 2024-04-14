package br.com.findfoodtosave.storemanagement.domain.exception;

public class BusinessException extends RuntimeException {
    
    public BusinessException(String message){
        super(message);
    }

}
