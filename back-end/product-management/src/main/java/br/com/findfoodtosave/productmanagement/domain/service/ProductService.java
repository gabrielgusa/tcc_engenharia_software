package br.com.findfoodtosave.productmanagement.domain.service;

import java.time.LocalDate;
import java.util.List;

import br.com.findfoodtosave.productmanagement.domain.exception.BusinessException;
import br.com.findfoodtosave.productmanagement.domain.model.Product;
import br.com.findfoodtosave.productmanagement.domain.repository.ProductRepository;
import br.com.findfoodtosave.productmanagement.domain.usecase.CreateProductUseCase;
import br.com.findfoodtosave.productmanagement.domain.usecase.DeleteProductByIdUseCase;
import br.com.findfoodtosave.productmanagement.domain.usecase.FindAllProductByIdStore;
import br.com.findfoodtosave.productmanagement.domain.usecase.FindAllProductsByIdUserAndIdStore;
import br.com.findfoodtosave.productmanagement.domain.usecase.FindProductById;
import br.com.findfoodtosave.productmanagement.domain.usecase.UpdateProductUseCase;

public class ProductService implements  CreateProductUseCase, 
                                        UpdateProductUseCase, 
                                        FindAllProductsByIdUserAndIdStore,
                                        DeleteProductByIdUseCase,
                                        FindProductById,
                                        FindAllProductByIdStore {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product create(String idUser, String idProduct, Product product) {
        product.validarDataDeValidade();

        product.setIdUser(idUser);
        product.setIdStore(idProduct);
        return this.productRepository.save(product);
    }

    @Override
    public Product update(String idProduct, String idUser, String idStore, Product product) {
        product.validarDataDeValidade();
        
        return this.productRepository.update(idProduct, idUser, idStore, product);
    }

    @Override
    public List<Product> findAllByIdUserAndIdStore(String idUser, String idStore) {
        return this.productRepository.findAllByIdUserAndIdStore(idUser, idStore);
    }

    @Override
    public void delete(String idProduct, String idUser, String idStore) {
        this.productRepository.delete(idProduct, idUser, idStore);
    }

    @Override
    public Product findById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> findAllByIdStore(String idStore) {
        return this.productRepository.findAllByIdStore(idStore);
    }

}
