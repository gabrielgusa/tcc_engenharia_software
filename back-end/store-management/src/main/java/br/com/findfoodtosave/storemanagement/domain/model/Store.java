package br.com.findfoodtosave.storemanagement.domain.model;

import java.time.LocalTime;

import lombok.Data;

@Data
public class Store {
    
    private String id;

    private String name;

    private LocalTime timeOpen;

    private LocalTime timeClose;
    
    private String idUser;

    private Address address;
    
}
