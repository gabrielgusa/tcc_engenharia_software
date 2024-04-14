package br.com.findfoodtosave.usermanagement.application.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {

    private String token;

    private long expiresIn;
    
}