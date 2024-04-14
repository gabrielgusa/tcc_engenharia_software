package br.com.findfoodtosave.productmanagement.domain.usecase;

import br.com.findfoodtosave.productmanagement.domain.model.Product;

public interface UpdateProductUseCase {

    Product update(String idProduct, String idUser, String idStore, Product product);
    
}
