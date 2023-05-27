package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleCreationUpdationRequest {

    @NotEmpty(message = "Brand name cannot be null or empty")
    private String brandName;

    @NotEmpty(message = "Model name cannot be null or empty")
    private String modelName;

    @Min(value = 1900, message = "Year should be greater than 1900")
    private int year;

    private String vin;

    @Min(value = 0, message = "Price should be greater than zero")
    private Double price;
}
