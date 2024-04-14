package br.com.findfoodtosave.storemanagement.application.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAddress {

    @NotNull(message = "Estado é obrigatorio.")
    @Size(min = 2, max = 2, message = "Estado deve conter de {min} caracteres.")
    private String state;

    @NotNull(message = "Cidade é obrigatorio.")
    @Size(min = 5, max = 45, message = "Cidade deve conter de {min} a {max} caracteres.")
    private String city;

    @NotNull(message = "Bairro é obrigatorio.")
    @Size(min = 5, max = 45, message = "Bairro deve conter de {min} a {max} caracteres.")
    private String district;

    @NotNull(message = "Rua é obrigatorio.")
    @Size(min = 5, max = 45, message = "Rua deve conter de {min} a {max} caracteres.")
    private String street;

    @Min(value = 0, message = "Número não pode ser menor que 0.")
    @Max(value = 9999, message = "Número não pode ser maior que 9999.")
    private int number;
    
    private String complement;

}
