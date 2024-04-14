package br.com.findfoodtosave.usermanagement.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.findfoodtosave.usermanagement.infrastructure.entity.UserEntity;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    
    Optional<UserEntity> findByEmail(String email);
    
}
