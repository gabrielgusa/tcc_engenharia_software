package br.com.findfoodtosave.productmanagement.infrastructure.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.findfoodtosave.productmanagement.domain.model.Product;
import br.com.findfoodtosave.productmanagement.domain.repository.ProductRepository;
import br.com.findfoodtosave.productmanagement.infrastructure.exception.EntityNotFoundException;
import br.com.findfoodtosave.productmanagement.infrastructure.mapper.InfrastructureProductMapper;
import br.com.findfoodtosave.productmanagement.infrastructure.repository.ProductJpaRepository;

@Component
public class ProductJpaRepositoryAdapter implements ProductRepository {

    @Autowired
    private InfrastructureProductMapper infrastructureProductMapper;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public Product save(Product product) {
        var productEntity = this.infrastructureProductMapper.toEntity(product);

        this.productJpaRepository.save(productEntity);

        return this.infrastructureProductMapper.toModel(productEntity);
    }

    @Override
    public Product update(String idProduct, String idUser, String idStore, Product product) {
        var productPersisted = this.findByIdAndIdUserAndIdStore(idProduct, idUser, idStore);

        productPersisted.setName(product.getName());
        productPersisted.setExpirationDate(product.getExpirationDate());
        productPersisted.setQuantity(product.getQuantity());

        this.productJpaRepository.save(this.infrastructureProductMapper.toEntity(productPersisted));

        return productPersisted;
    }

    @Override
    public List<Product> findAllByIdUserAndIdStore(String idUser, String idStore) {
        return this.infrastructureProductMapper.toModel(this.productJpaRepository.findAllByIdUserAndIdStore(idUser, idStore));
    }

    @Override
    public void delete(String idProduct, String idUser, String idStore) {
        var product = this.findByIdAndIdUserAndIdStore(idProduct, idUser, idStore);
        this.productJpaRepository.deleteById(product.getId());
    }

    @Override
    public Product findById(String id) {
        var optionalProduct = this.productJpaRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new EntityNotFoundException("Produto não existe.");
        }

        return this.infrastructureProductMapper.toModel(optionalProduct.get());
    }

    @Override
    public List<Product> findAllByIdStore(String idStore) {
        return this.infrastructureProductMapper.toModel(this.productJpaRepository.findAllByIdStore(idStore));
    }

    @Override
    public Product findByIdAndIdUserAndIdStore(String id, String idUser, String idStore) {
        var product = this.productJpaRepository.findByIdAndIdUserAndIdStore(id, idUser, idStore);
        
        if(product.isEmpty()) {
            throw new EntityNotFoundException("Produto não encontrado.");
        }

        return this.infrastructureProductMapper.toModel(product.get());
    }

    

}
