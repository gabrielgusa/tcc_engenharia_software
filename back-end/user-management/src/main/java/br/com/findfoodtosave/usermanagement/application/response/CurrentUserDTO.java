package br.com.findfoodtosave.usermanagement.application.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentUserDTO {
    
    private String id;
    
    private String name;

    private String email;

}
