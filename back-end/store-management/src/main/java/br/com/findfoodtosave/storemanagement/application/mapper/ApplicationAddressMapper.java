package br.com.findfoodtosave.storemanagement.application.mapper;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.storemanagement.application.response.AddressDTO;
import br.com.findfoodtosave.storemanagement.domain.model.Address;

@Mapper
public interface ApplicationAddressMapper {
    
    Address toModel(AddressDTO addressDTO);
    
}
