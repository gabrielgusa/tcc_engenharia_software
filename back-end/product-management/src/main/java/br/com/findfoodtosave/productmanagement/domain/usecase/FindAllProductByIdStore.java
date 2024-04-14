package br.com.findfoodtosave.productmanagement.domain.usecase;

import java.util.List;

import br.com.findfoodtosave.productmanagement.domain.model.Product;

public interface FindAllProductByIdStore {

    List<Product> findAllByIdStore(String idStore);
    
}
