package br.com.findfoodtosave.storemanagement.application.request;

import java.time.LocalTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateStore {

    @NotNull(message = "Nome é obrigatorio.")
    @Size(min = 5, max = 45, message = "Nome deve conter de {min} a {max} caracteres.")
    private String name;

    @NotNull(message = "Horario de abertura é obrigatorio.")
    private LocalTime timeOpen;

    @NotNull(message = "Horario de fechamento é obrigatorio.")
    private LocalTime timeClose;

    @NotNull(message = "Endereço é obrigatorio.")
    @Valid
    private CreateAddress address;
    
}
