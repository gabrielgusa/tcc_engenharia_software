package br.com.findfoodtosave.usermanagement.application.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.findfoodtosave.usermanagement.application.mapper.ApplicationUserMapper;
import br.com.findfoodtosave.usermanagement.application.request.UserDTO;
import br.com.findfoodtosave.usermanagement.application.response.CurrentUserDTO;
import br.com.findfoodtosave.usermanagement.domain.usecase.SaveUserUseCase;
import jakarta.validation.Valid;

@RestController
public class UserController {
    
    private final ApplicationUserMapper applicationUserMapper;
    private final SaveUserUseCase saveUserUseCase;
    
    public UserController(
        ApplicationUserMapper applicationUserMapper,
        SaveUserUseCase saveUserUseCase
    ){
        this.applicationUserMapper = applicationUserMapper;
        this.saveUserUseCase = saveUserUseCase;
    }
    
    @PostMapping(path = "/api/v1/users")
    public CurrentUserDTO save(@Valid @RequestBody UserDTO userDTO){
        return this.applicationUserMapper.toUserDetails(this.saveUserUseCase.save(this.applicationUserMapper.toModel(userDTO)));
    }

}
