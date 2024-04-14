package br.com.findfoodtosave.storemanagement.application.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    
    private String id;

    private String state;

    private String city;

    private String district;

    private String street;

    private int number;

    private String complement;

}
