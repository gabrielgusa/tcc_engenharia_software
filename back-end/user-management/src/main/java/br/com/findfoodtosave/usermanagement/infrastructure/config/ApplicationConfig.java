package br.com.findfoodtosave.usermanagement.infrastructure.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.findfoodtosave.usermanagement.application.mapper.ApplicationUserMapper;
import br.com.findfoodtosave.usermanagement.domain.service.AuthenticationService;
import br.com.findfoodtosave.usermanagement.domain.service.UserService;
import br.com.findfoodtosave.usermanagement.infrastructure.adapter.UserJpaRepositoryAdapter;
import br.com.findfoodtosave.usermanagement.infrastructure.exception.UserNotFoundException;
import br.com.findfoodtosave.usermanagement.infrastructure.mapper.InfrastructureUserMapper;

@Configuration
public class ApplicationConfig {

    @Bean
    public ApplicationUserMapper applicationUserMapper(){
        return Mappers.getMapper(ApplicationUserMapper.class);
    }

    @Bean
    public InfrastructureUserMapper infrastructureUserMapper(){
        return Mappers.getMapper(InfrastructureUserMapper.class);
    }

    @Bean
    public UserService userService(
        UserJpaRepositoryAdapter userJpaRepositoryAdapter,
        PasswordEncoder passwordEncoder
    ){
        return new UserService(userJpaRepositoryAdapter, passwordEncoder);
    }

    @Bean
    public AuthenticationService authenticationService(
        UserJpaRepositoryAdapter userJpaRepositoryAdapter,
        AuthenticationManager authenticationManager
    ){
        return new AuthenticationService(userJpaRepositoryAdapter, authenticationManager);
    }

    @Bean
    UserDetailsService userDetailsService(UserJpaRepositoryAdapter userJpaRepositoryAdapter) {
        return username -> userJpaRepositoryAdapter.findByEmail(username).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserJpaRepositoryAdapter jpaUserRepositoryAdapter) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService(jpaUserRepositoryAdapter));
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
