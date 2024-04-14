package br.com.findfoodtosave.productmanagement.domain.usecase;

public interface DeleteProductByIdUseCase {
    
    void delete(String idProduct, String idUser, String idStore);
    
}
