package br.com.findfoodtosave.usermanagement.domain.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.findfoodtosave.usermanagement.domain.model.User;
import br.com.findfoodtosave.usermanagement.domain.repository.UserRepository;
import br.com.findfoodtosave.usermanagement.domain.usecase.SaveUserUseCase;
import br.com.findfoodtosave.usermanagement.infrastructure.exception.UserAlreadyExistsException;

public class UserService implements SaveUserUseCase {

    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;

    public UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        if(this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email is already registered.");
        }
        
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }
    
}
