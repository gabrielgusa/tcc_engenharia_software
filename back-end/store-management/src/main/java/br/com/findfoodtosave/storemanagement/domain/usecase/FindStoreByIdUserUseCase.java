package br.com.findfoodtosave.storemanagement.domain.usecase;

import br.com.findfoodtosave.storemanagement.domain.model.Store;

public interface FindStoreByIdUserUseCase {
    
    Store findByIdUser(String idUser);
    
}
