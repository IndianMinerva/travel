package com.example.travel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "MODEL")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_generator")
    @SequenceGenerator(name= "model_generator", sequenceName = "model_seq", allocationSize = 1)
    private Long Id;

    @Column(unique = true)
    private String name;

    public Model(String name) {
        this.name = name;
    }
}
