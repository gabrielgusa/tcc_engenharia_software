package br.com.findfoodtosave.storemanagement.domain.usecase;

import br.com.findfoodtosave.storemanagement.domain.model.Store;

public interface CreateStoreUseCase {
    
    Store create(String idUser, Store store);

}
