package com.example.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "BRAND")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    @SequenceGenerator(name= "brand_generator", sequenceName = "brand_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
