package com.example.travel.model;

import jakarta.persistence.*;
import lombok.*;

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
    @Setter
    private Contract contract;


    private int model_year;

    private String vin;

    private Double price;
}
