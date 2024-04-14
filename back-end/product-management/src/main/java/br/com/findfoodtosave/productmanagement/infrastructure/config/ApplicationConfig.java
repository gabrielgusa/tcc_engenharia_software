package br.com.findfoodtosave.productmanagement.infrastructure.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.findfoodtosave.productmanagement.application.mapper.ApplicationProductMapper;
import br.com.findfoodtosave.productmanagement.domain.repository.ProductRepository;
import br.com.findfoodtosave.productmanagement.domain.service.ProductService;
import br.com.findfoodtosave.productmanagement.infrastructure.mapper.InfrastructureProductMapper;

@Component
public class ApplicationConfig {
    
    @Bean
    public InfrastructureProductMapper infrastructureProductMapper(){
        return Mappers.getMapper(InfrastructureProductMapper.class);
    }
    
    @Bean
    public ApplicationProductMapper applicationProductMapper(){
        return Mappers.getMapper(ApplicationProductMapper.class);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

}
