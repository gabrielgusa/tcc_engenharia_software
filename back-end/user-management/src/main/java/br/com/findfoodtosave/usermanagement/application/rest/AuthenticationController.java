package br.com.findfoodtosave.usermanagement.application.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.findfoodtosave.usermanagement.application.mapper.ApplicationUserMapper;
import br.com.findfoodtosave.usermanagement.application.request.LoginDTO;
import br.com.findfoodtosave.usermanagement.application.response.CurrentUserDTO;
import br.com.findfoodtosave.usermanagement.application.response.TokenDTO;
import br.com.findfoodtosave.usermanagement.domain.model.User;
import br.com.findfoodtosave.usermanagement.domain.service.AuthenticationService;
import br.com.findfoodtosave.usermanagement.infrastructure.adapter.JwtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping
public class AuthenticationController {
    
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    private final ApplicationUserMapper applicationUserMapper;

    public AuthenticationController(
        JwtService jwtService, 
        AuthenticationService authenticationService,
        ApplicationUserMapper applicationUserMapper
    ) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.applicationUserMapper = applicationUserMapper;
    }

    @PostMapping(path = "/api/v1/login")
    public TokenDTO authenticate(@Valid @RequestBody LoginDTO loginDTO) {
        UserDetails authenticatedUser = this.authenticationService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());

        String jwtToken = this.jwtService.generateToken(authenticatedUser);

        var loginResponse = TokenDTO.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return loginResponse;
    }

    @GetMapping("/api/v1/users/me")
    public CurrentUserDTO authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        var currentUser = this.applicationUserMapper.toUserDetails((User)authentication.getPrincipal());

        return currentUser;
    }

}
