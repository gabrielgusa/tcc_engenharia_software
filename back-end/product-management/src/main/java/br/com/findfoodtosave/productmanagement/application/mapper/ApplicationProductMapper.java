package br.com.findfoodtosave.productmanagement.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.productmanagement.application.request.CreateProductDTO;
import br.com.findfoodtosave.productmanagement.application.request.UpdateProductDTO;
import br.com.findfoodtosave.productmanagement.application.response.ProductDTO;
import br.com.findfoodtosave.productmanagement.domain.model.Product;

@Mapper
public interface ApplicationProductMapper {
    
    Product toModel(CreateProductDTO createProduct);

    Product toModel(UpdateProductDTO updateProductDTO);

    ProductDTO toDTO(Product product);

    List<ProductDTO> toDTO(List<Product> product);

}
