package com.example.travel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "BRAND")
public class Brand {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    @Column(unique = true)
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
