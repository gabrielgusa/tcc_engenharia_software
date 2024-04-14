package br.com.findfoodtosave.productmanagement.application.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateProductDTO {
    
    @NotNull(message = "Nome é obrigatorio.")
    @Size(min = 5, max = 45, message = "Nome deve conter de {min} a {max} caracteres.")
    private String name;

    @NotNull(message = "Data de validade é obrigatorio.")
    private LocalDate expirationDate;

    @Min(value = 0, message = "Quantidade não pode ser menor que 0.")
    @Max(value = 99, message = "Quantidade não pode ser maior que 99.")
    private int quantity;
    
}
