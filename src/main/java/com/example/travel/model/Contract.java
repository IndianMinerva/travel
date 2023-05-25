package com.example.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Contract {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    /*@OneToMany
    @JoinTable(
            name="CONTRACT_VEHICLE",
            joinColumns = @JoinColumn( name="contract_id"),
            inverseJoinColumns = @JoinColumn( name="vehicle_id")
    )
    private List<Vehicle> vehicles;*/

    private Double rate;

    public Contract(Customer customer, Double rate) {
        this.customer = customer;
        this.rate = rate;
    }
}
