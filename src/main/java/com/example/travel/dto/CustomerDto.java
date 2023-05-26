package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class CustomerDto {
    private Long Id;

    private String firstName;

    private String lastName;

    private Date dob;
}
