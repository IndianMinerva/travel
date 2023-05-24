package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerDto {
    private Long Id;

    private String firstName;

    private String lastName;

    private Date dob;
}
