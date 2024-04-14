package br.com.findfoodtosave.storemanagement.domain.usecase;

import java.util.List;

import br.com.findfoodtosave.storemanagement.domain.model.Address;
import br.com.findfoodtosave.storemanagement.domain.model.Store;

public interface FindStoreByAddressUseCase {
    
    List<Store> findAllByAddress(Address address);
    
}
