package com.example.travel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerCreationUpdationRequest {
    private String firstName;
    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date dob;
}
