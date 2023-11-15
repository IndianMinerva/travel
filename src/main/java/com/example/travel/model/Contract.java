package com.example.travel.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_generator")
    @SequenceGenerator(name= "contract_generator", sequenceName = "contract_seq", allocationSize = 1)
    private Long id;

    @Version
    private Long version;

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "contract")
    @Setter
    private List<Vehicle> vehicles;

    private Double rate;

    public Contract(Customer customer, Double rate) {
        this.customer = customer;
        this.rate = rate;
    }
}
