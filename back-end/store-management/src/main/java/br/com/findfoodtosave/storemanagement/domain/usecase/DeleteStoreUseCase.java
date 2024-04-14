package br.com.findfoodtosave.storemanagement.domain.usecase;

public interface DeleteStoreUseCase {

    void deleteByIdAndIdUser(String id, String idUser);
    
}
