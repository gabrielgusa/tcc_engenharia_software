package br.com.findfoodtosave.storemanagement.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.findfoodtosave.storemanagement.infrastructure.entity.StoreEntity;

@Repository
public interface StoreJpaRepository extends JpaRepository<StoreEntity, String> {

    @Query("SELECT s FROM StoreEntity s WHERE s.address.state = ?1 OR s.address.city = ?2 OR s.address.district = ?3")
    List<StoreEntity> findAllByAddress(String state, String city, String district);
    
    Optional<StoreEntity> findByIdUser(String idUser);

    Optional<StoreEntity> findByIdAndIdUser(String id, String idUser);
    
    void deleteByIdAndIdUser(String id, String idUser);
    
}
