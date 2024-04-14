package br.com.findfoodtosave.storemanagement.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.storemanagement.application.request.CreateStore;
import br.com.findfoodtosave.storemanagement.application.response.StoreDTO;
import br.com.findfoodtosave.storemanagement.domain.model.Store;

@Mapper
public interface ApplicationStoreMapper {
    
    Store toModel(CreateStore createStore);
    
    StoreDTO toDTO(Store store);
    
    List<StoreDTO> toDTO(List<Store> stores);

}
