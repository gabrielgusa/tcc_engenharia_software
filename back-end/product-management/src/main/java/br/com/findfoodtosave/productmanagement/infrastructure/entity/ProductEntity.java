package br.com.findfoodtosave.productmanagement.infrastructure.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "product")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "id_store")
    private String idStore;

}
