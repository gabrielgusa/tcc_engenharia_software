package br.com.findfoodtosave.productmanagement.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.findfoodtosave.productmanagement.infrastructure.entity.ProductEntity;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {
    
    Optional<ProductEntity> findByIdAndIdUserAndIdStore(String id, String idUser, String idStore);

    List<ProductEntity> findAllByIdUserAndIdStore(String idUser, String idStore);

    List<ProductEntity> findAllByIdStore(String idStore);
    
}
