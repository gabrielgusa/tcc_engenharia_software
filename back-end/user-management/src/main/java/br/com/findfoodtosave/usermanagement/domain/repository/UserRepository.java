package br.com.findfoodtosave.usermanagement.domain.repository;

import java.util.Optional;

import br.com.findfoodtosave.usermanagement.domain.model.User;

public interface UserRepository {
    
    User save(User user);
    
    Optional<User> findByEmail(String email);
    
}
