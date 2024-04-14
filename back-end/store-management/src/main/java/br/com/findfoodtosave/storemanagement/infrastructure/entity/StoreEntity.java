package br.com.findfoodtosave.storemanagement.infrastructure.entity;

import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "store_entity")
public class StoreEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "time_open")
    private LocalTime timeOpen;

    @Column(name = "time_close")
    private LocalTime timeClose;

    @Column(name = "id_user")
    private String idUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_address", referencedColumnName = "id")
    private AddressEntity address;

}
