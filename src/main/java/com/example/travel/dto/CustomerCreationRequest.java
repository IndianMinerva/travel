package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerCreationRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String dob;
}
