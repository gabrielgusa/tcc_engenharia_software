package br.com.findfoodtosave.productmanagement.application.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.findfoodtosave.productmanagement.application.mapper.ApplicationProductMapper;
import br.com.findfoodtosave.productmanagement.application.request.CreateProductDTO;
import br.com.findfoodtosave.productmanagement.application.request.UpdateProductDTO;
import br.com.findfoodtosave.productmanagement.application.response.ProductDTO;
import br.com.findfoodtosave.productmanagement.domain.usecase.CreateProductUseCase;
import br.com.findfoodtosave.productmanagement.domain.usecase.DeleteProductByIdUseCase;
import br.com.findfoodtosave.productmanagement.domain.usecase.FindAllProductByIdStore;
import br.com.findfoodtosave.productmanagement.domain.usecase.FindAllProductsByIdUserAndIdStore;
import br.com.findfoodtosave.productmanagement.domain.usecase.FindProductById;
import br.com.findfoodtosave.productmanagement.domain.usecase.UpdateProductUseCase;
import jakarta.validation.Valid;

@RestController
public class ProductController {
    
    @Autowired
    private ApplicationProductMapper applicationProductMapper;
    
    @Autowired
    private CreateProductUseCase createProductUseCase;
    
    @Autowired
    private UpdateProductUseCase updateProductUseCase;

    @Autowired
    private DeleteProductByIdUseCase deleteProductByIdUseCase;

    @Autowired
    private FindAllProductsByIdUserAndIdStore findAllProductsByIdUserAndIdStore;

    @Autowired
    private FindProductById findProductById;

    @Autowired
    private FindAllProductByIdStore findAllProductByIdStore;

    @PostMapping(path = "/api/v1/users/{id_user}/stores/{id_store}/products")
    public ProductDTO save(
        @Valid @RequestBody CreateProductDTO createProduct,
        @PathVariable(name = "id_user", required = true) String idUser,
        @PathVariable(name = "id_store", required = true) String idStore
    ){
        return this.applicationProductMapper.toDTO(this.createProductUseCase.create(idUser, idStore, this.applicationProductMapper.toModel(createProduct)));
    }

    @PutMapping(path = "/api/v1/users/{id_user}/stores/{id_store}/products/{id_product}")
    public ProductDTO update(
        @Valid @RequestBody UpdateProductDTO updateProductDTO,
        @PathVariable(name = "id_user", required = true) String idUser,
        @PathVariable(name = "id_store", required = true) String idStore,
        @PathVariable(name = "id_product", required = true) String idProduct
    ){
        return this.applicationProductMapper.toDTO(this.updateProductUseCase.update(idProduct, idUser, idStore, this.applicationProductMapper.toModel(updateProductDTO)));
    }

    @DeleteMapping(path = "/api/v1/users/{id_user}/stores/{id_store}/products/{id_product}")
    public void delete(
        @PathVariable(name = "id_user", required = true) String idUser,
        @PathVariable(name = "id_store", required = true) String idStore,
        @PathVariable(name = "id_product", required = true) String idProduct
    ){
        this.deleteProductByIdUseCase.delete(idProduct, idUser, idStore);
    }
    
    @GetMapping(path = "/api/v1/users/{id_user}/stores/{id_store}/products/{id_product}")
    public ProductDTO findById(
        @PathVariable(name = "id_user", required = true) String idUser,
        @PathVariable(name = "id_store", required = true) String idStore,
        @PathVariable(name = "id_product", required = true) String idProduct
    ){
        return this.applicationProductMapper.toDTO(this.findProductById.findById(idProduct));
    }

    @GetMapping(path = "/api/v1/users/{id_user}/stores/{id_store}/products")
    public List<ProductDTO> findAllByIdUserAndIdStore(
        @PathVariable(name = "id_user", required = true) String idUser,
        @PathVariable(name = "id_store", required = true) String idStore
    ){
        return this.applicationProductMapper.toDTO(this.findAllProductsByIdUserAndIdStore.findAllByIdUserAndIdStore(idUser, idStore));
    }
    
    @GetMapping(path = "/api/v1/stores/{id_store}/products")
    public List<ProductDTO> findAllByIdStore(
        @PathVariable(name = "id_store", required = true) String idStore
    ){
        return this.applicationProductMapper.toDTO(this.findAllProductByIdStore.findAllByIdStore(idStore));
    }

}
