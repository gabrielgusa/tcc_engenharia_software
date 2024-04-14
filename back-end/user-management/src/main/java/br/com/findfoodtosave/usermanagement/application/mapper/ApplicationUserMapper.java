package br.com.findfoodtosave.usermanagement.application.mapper;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.usermanagement.application.request.UserDTO;
import br.com.findfoodtosave.usermanagement.application.response.CurrentUserDTO;
import br.com.findfoodtosave.usermanagement.domain.model.User;

@Mapper
public interface ApplicationUserMapper {

    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);
    
    CurrentUserDTO toUserDetails(User user);

}
