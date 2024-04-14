package br.com.findfoodtosave.storemanagement.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.storemanagement.domain.model.Store;
import br.com.findfoodtosave.storemanagement.infrastructure.entity.StoreEntity;

@Mapper
public interface InfrastructureStoreMapper {
    
    Store toModel(StoreEntity storeEntity);

    List<Store> toModel(List<StoreEntity> stores);

    StoreEntity toEntity(Store store);

}
