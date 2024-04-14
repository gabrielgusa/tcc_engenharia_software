package br.com.findfoodtosave.productmanagement.domain.usecase;

import br.com.findfoodtosave.productmanagement.domain.model.Product;

public interface FindProductById {
    
    Product findById(String id);

}
