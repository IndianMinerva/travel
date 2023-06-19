package com.example.travel.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Audited
public class Contract {

    @Id
    @GeneratedValue
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
