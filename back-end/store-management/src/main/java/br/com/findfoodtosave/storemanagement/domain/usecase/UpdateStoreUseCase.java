package br.com.findfoodtosave.storemanagement.domain.usecase;

import br.com.findfoodtosave.storemanagement.domain.model.Store;

public interface UpdateStoreUseCase {

    Store update(String id, String idUser, Store store);
    
}
