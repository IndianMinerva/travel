package com.example.travel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;


    private int model_year;

    private String vin;

    private Double price;
}
