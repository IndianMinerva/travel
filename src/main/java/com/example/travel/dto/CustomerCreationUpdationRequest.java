package com.example.travel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomerCreationUpdationRequest {
    @NotEmpty(message = "First name cannot be null or empty")
    private String firstName;

    @NotEmpty(message = "Last name cannot be null or empty")
    private String lastName;

    @JsonFormat(pattern = "dd.MM.yyyy")
    @ApiModelProperty(notes = "Customer's DOB", example = "23.12.2000", required = true, value = "01.01.2000")
    private Date dob;
}
