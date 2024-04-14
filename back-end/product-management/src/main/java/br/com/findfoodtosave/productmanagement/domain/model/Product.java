package br.com.findfoodtosave.productmanagement.domain.model;

import java.time.LocalDate;

import br.com.findfoodtosave.productmanagement.domain.exception.BusinessException;
import lombok.Data;

@Data
public class Product {
    
    private String id;

    private String name;

    private LocalDate expirationDate;

    private int quantity;

    private String idUser;
    
    private String idStore;
    
    public void validarDataDeValidade(){
        if(this.expirationDate.isBefore(LocalDate.now())){
            throw new BusinessException("Data de validade não pode ser anterior a data atual.");
        }
    }

}
