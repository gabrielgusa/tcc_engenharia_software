package br.com.findfoodtosave.productmanagement.application.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProductDTO {

    private String id;

    private String name;

    private LocalDate expirationDate;

    private int quantity;

}
