package br.com.findfoodtosave.productmanagement.domain.usecase;

import br.com.findfoodtosave.productmanagement.domain.model.Product;

public interface CreateProductUseCase {
    
    Product create(String idUser, String idProduct, Product product);
    
}
