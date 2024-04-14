package br.com.findfoodtosave.storemanagement.domain.repository;

import java.util.List;
import java.util.Optional;

import br.com.findfoodtosave.storemanagement.domain.model.Address;
import br.com.findfoodtosave.storemanagement.domain.model.Store;

public interface StoreRepository {
    
    Store save(Store store);

    Store update(String id, String idUser, Store store);
    
    Store findByIdAndIdUser(String id, String idUser);

    Optional<Store> findByIdUser(String idUser);
    
    List<Store> findAllByAddress(Address address);

    void deleteByIdAndIdUser(String id, String idUser);
    
}
