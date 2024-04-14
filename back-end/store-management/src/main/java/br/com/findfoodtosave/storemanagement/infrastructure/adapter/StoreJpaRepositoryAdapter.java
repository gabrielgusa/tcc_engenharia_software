package br.com.findfoodtosave.storemanagement.infrastructure.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.findfoodtosave.storemanagement.domain.model.Address;
import br.com.findfoodtosave.storemanagement.domain.model.Store;
import br.com.findfoodtosave.storemanagement.domain.repository.StoreRepository;
import br.com.findfoodtosave.storemanagement.infrastructure.exception.EntityNotFoundException;
import br.com.findfoodtosave.storemanagement.infrastructure.mapper.InfrastructureStoreMapper;
import br.com.findfoodtosave.storemanagement.infrastructure.repository.StoreJpaRepository;

@Component
public class StoreJpaRepositoryAdapter implements StoreRepository {

    @Autowired
    private StoreJpaRepository storeJpaRepository;

    @Autowired
    private InfrastructureStoreMapper infrastructureStoreMapper;

    @Override
    public Store save(Store store) {
        var storeEntity = this.infrastructureStoreMapper.toEntity(store);

        this.storeJpaRepository.save(storeEntity);

        return this.infrastructureStoreMapper.toModel(storeEntity);
    }

    @Override
    public Store update(String id, String idUser, Store store) {
        var storePersisted = this.findByIdAndIdUser(id, idUser);

        storePersisted.setName(store.getName());
        storePersisted.setTimeOpen(store.getTimeOpen());
        storePersisted.setTimeClose(store.getTimeClose());
        
        var addressPersisted = storePersisted.getAddress();
        addressPersisted.setState(store.getAddress().getState());
        addressPersisted.setCity(store.getAddress().getCity());
        addressPersisted.setDistrict(store.getAddress().getDistrict());
        addressPersisted.setStreet(store.getAddress().getStreet());
        addressPersisted.setNumber(store.getAddress().getNumber());
        addressPersisted.setComplement(store.getAddress().getComplement());

        var storeEntity = this.storeJpaRepository.save(this.infrastructureStoreMapper.toEntity(storePersisted));

        return this.infrastructureStoreMapper.toModel(storeEntity);
    }
    
    @Override
    public void deleteByIdAndIdUser(String id, String idUser){
        var storePersisted = this.findByIdAndIdUser(id, idUser);
        this.storeJpaRepository.delete(this.infrastructureStoreMapper.toEntity(storePersisted));
    }

    @Override
    public Store findByIdAndIdUser(String id, String idUser) {
        var storeEntity = this.storeJpaRepository.findByIdAndIdUser(id, idUser);
        
        if(storeEntity.isEmpty()) {
            throw new EntityNotFoundException("Estabelecimento não encontrado.");
        }

        return this.infrastructureStoreMapper.toModel(storeEntity.get());
    }

    @Override
    public Optional<Store> findByIdUser(String idUser) {
        var storeEntity = this.storeJpaRepository.findByIdUser(idUser);

        if(storeEntity.isEmpty()) return Optional.empty();

        return Optional.of(this.infrastructureStoreMapper.toModel(storeEntity.get()));
    }

    @Override
    public List<Store> findAllByAddress(Address address) {
        var stores = this.storeJpaRepository.findAllByAddress(address.getState(), address.getCity(), address.getDistrict());

        return this.infrastructureStoreMapper.toModel(stores);
    }
    
}
