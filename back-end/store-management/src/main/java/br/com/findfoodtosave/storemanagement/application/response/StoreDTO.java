package br.com.findfoodtosave.storemanagement.application.response;

import java.time.LocalTime;

import lombok.Data;

@Data
public class StoreDTO {
    
    private String id;
    
    private String name;

    private LocalTime timeOpen;

    private LocalTime timeClose;

    private AddressDTO address;
    
}
