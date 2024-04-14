package br.com.findfoodtosave.storemanagement.domain.model;

import lombok.Data;

@Data
public class Address {
    
    private String id;

    private String state;

    private String city;

    private String district;

    private String street;

    private int number;

    private String complement;

    private String idStore;
    
}
