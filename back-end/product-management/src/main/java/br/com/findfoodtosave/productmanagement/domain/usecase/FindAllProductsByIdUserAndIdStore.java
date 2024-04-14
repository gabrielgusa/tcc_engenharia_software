package br.com.findfoodtosave.productmanagement.domain.usecase;

import java.util.List;

import br.com.findfoodtosave.productmanagement.domain.model.Product;

public interface FindAllProductsByIdUserAndIdStore {
    
    List<Product> findAllByIdUserAndIdStore(String idUser, String idStore);
}
