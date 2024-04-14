package br.com.findfoodtosave.productmanagement.domain.repository;

import java.util.List;

import br.com.findfoodtosave.productmanagement.domain.model.Product;

public interface ProductRepository {
    
    Product save(Product product);

    Product update(String idProduct, String idUser, String idStore, Product product);

    void delete(String idProduct, String idUser, String idStore);

    Product findById(String id);

    Product findByIdAndIdUserAndIdStore(String id, String idUser, String idStore);

    List<Product> findAllByIdUserAndIdStore(String idUser, String idStore);

    List<Product> findAllByIdStore(String idStore);

}
