package com.example.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contract_id")
    private Contract contract;


    private int model_year;

    private String vin;

    private Double price;
}
