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

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Model model;

/*    @ManyToOne(fetch = FetchType.LAZY)
    private LeasingContract leasingContract;*/


    private int year;

    private String vin;

    private Double price;

    //private Contract contract;
}
