package com.example.travel.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    @Schema(type = "string", pattern = "dd.MM-.yyyy", example = "17.02.2020")
    private Date dateOfBirth;
}