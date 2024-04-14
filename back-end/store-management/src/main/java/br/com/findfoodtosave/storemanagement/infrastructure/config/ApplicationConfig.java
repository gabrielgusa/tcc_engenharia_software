package br.com.findfoodtosave.storemanagement.infrastructure.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.findfoodtosave.storemanagement.application.mapper.ApplicationAddressMapper;
import br.com.findfoodtosave.storemanagement.application.mapper.ApplicationStoreMapper;
import br.com.findfoodtosave.storemanagement.domain.repository.StoreRepository;
import br.com.findfoodtosave.storemanagement.domain.service.StoreService;
import br.com.findfoodtosave.storemanagement.infrastructure.mapper.InfrastructureStoreMapper;

@Component
public class ApplicationConfig {

    @Bean
    public ApplicationAddressMapper applicationAddressMapper(){
        return Mappers.getMapper(ApplicationAddressMapper.class);
    }

    @Bean
    public ApplicationStoreMapper applicationStoreMapper(){
        return Mappers.getMapper(ApplicationStoreMapper.class);
    }

    @Bean
    public InfrastructureStoreMapper infrastructureStoreMapper(){
        return Mappers.getMapper(InfrastructureStoreMapper.class);
    }

    @Bean
    public StoreService storeService(StoreRepository storeRepository){
        return new StoreService(storeRepository);
    }
    
}
