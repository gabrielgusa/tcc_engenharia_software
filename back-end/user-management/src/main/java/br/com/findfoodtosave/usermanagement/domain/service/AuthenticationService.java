package br.com.findfoodtosave.usermanagement.domain.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.findfoodtosave.usermanagement.domain.model.User;
import br.com.findfoodtosave.usermanagement.domain.repository.UserRepository;

public class AuthenticationService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public User authenticate(String email, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return this.userRepository.findByEmail(email).get();
    }

}
