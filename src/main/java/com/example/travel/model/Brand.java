package com.example.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Brand {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(unique = true)
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
