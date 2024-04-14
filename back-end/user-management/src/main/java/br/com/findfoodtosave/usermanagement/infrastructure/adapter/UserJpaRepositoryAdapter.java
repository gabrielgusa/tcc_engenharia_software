package br.com.findfoodtosave.usermanagement.infrastructure.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.findfoodtosave.usermanagement.domain.model.User;
import br.com.findfoodtosave.usermanagement.domain.repository.UserRepository;
import br.com.findfoodtosave.usermanagement.infrastructure.mapper.InfrastructureUserMapper;
import br.com.findfoodtosave.usermanagement.infrastructure.repository.UserJpaRepository;

@Component
public class UserJpaRepositoryAdapter implements UserRepository {

    @Autowired
    private UserJpaRepository jpaUserRepository;

    @Autowired
    private InfrastructureUserMapper infrastructureUserMapper;

    @Override
    public User save(User user) {
       var userEntity = this.infrastructureUserMapper.toEntity(user);
        
       var userPersisted = this.jpaUserRepository.save(userEntity);
       
       return this.infrastructureUserMapper.toModel(userPersisted);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        var optionalUser = this.jpaUserRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            return Optional.empty();
        }

        var user = this.infrastructureUserMapper.toModel(optionalUser.get());

        return Optional.of(user);
    }
    
}
