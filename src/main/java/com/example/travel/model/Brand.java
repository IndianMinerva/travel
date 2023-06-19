package com.example.travel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "BRAND")
public class Brand {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
