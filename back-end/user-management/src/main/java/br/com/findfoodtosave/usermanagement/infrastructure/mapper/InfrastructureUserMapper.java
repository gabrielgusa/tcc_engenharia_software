package br.com.findfoodtosave.usermanagement.infrastructure.mapper;

import org.mapstruct.Mapper;

import br.com.findfoodtosave.usermanagement.domain.model.User;
import br.com.findfoodtosave.usermanagement.infrastructure.entity.UserEntity;

@Mapper
public interface InfrastructureUserMapper {
    
    UserEntity toEntity(User user);

    User toModel(UserEntity userEntity);
    
}
