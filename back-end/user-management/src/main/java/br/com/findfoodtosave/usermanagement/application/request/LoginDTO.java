package br.com.findfoodtosave.usermanagement.application.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    
    @NotNull(message = "Email é obrigatorio.")
    @Size(min = 5, max = 45, message = "Email deve conter de {min} a {max} caracteres.")
    private String email;

    @NotNull(message = "Senha é obrigatorio.")
    @Size(min = 5, max = 45, message = "Senha deve conter de {min} a {max} caracteres.")
    private String password;
    
}
