package com.example.travel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Model {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(unique = true)
    private String name;

    public Model(String name) {
        this.name = name;
    }
}
