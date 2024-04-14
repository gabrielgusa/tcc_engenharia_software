package br.com.findfoodtosave.productmanagement.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.productmanagement.domain.model.Product;
import br.com.findfoodtosave.productmanagement.infrastructure.entity.ProductEntity;

@Mapper
public interface InfrastructureProductMapper {
    
    ProductEntity toEntity(Product product);

    Product toModel(ProductEntity productEntity);

    List<Product> toModel(List<ProductEntity> products);
    
}
