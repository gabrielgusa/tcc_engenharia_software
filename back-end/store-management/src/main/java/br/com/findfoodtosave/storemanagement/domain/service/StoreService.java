package br.com.findfoodtosave.storemanagement.domain.service;

import java.util.List;

import br.com.findfoodtosave.storemanagement.domain.exception.BusinessException;
import br.com.findfoodtosave.storemanagement.domain.model.Address;
import br.com.findfoodtosave.storemanagement.domain.model.Store;
import br.com.findfoodtosave.storemanagement.domain.repository.StoreRepository;
import br.com.findfoodtosave.storemanagement.domain.usecase.CreateStoreUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.DeleteStoreUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.FindStoreByAddressUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.UpdateStoreUseCase;
import br.com.findfoodtosave.storemanagement.domain.usecase.FindStoreByIdUserUseCase;

public class StoreService implements CreateStoreUseCase, UpdateStoreUseCase, FindStoreByAddressUseCase, FindStoreByIdUserUseCase, DeleteStoreUseCase {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public Store create(String idUser, Store store) {
        if(this.storeRepository.findByIdUser(idUser).isPresent()){
            throw new BusinessException("Usuário já possui estabelecimento cadastrado.");
        }
        store.setIdUser(idUser);
        return this.storeRepository.save(store);
    }

    @Override
    public Store update(String id, String idUser, Store store) {
        return this.storeRepository.update(id, idUser, store);
    }

    @Override
    public void deleteByIdAndIdUser(String id, String idUser){
        this.storeRepository.deleteByIdAndIdUser(id, idUser);
    }

    @Override
    public Store findByIdUser(String idUser) {
        if(this.storeRepository.findByIdUser(idUser).isEmpty()) {
            throw new BusinessException("Nenhum estabelecimento encontrado para o usuário.");
        }
        return this.storeRepository.findByIdUser(idUser).get();
    }

    @Override
    public List<Store> findAllByAddress(Address address) {
        return this.storeRepository.findAllByAddress(address);
    }
    
}
