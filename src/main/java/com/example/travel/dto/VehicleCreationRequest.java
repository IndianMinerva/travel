package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleCreationRequest {
    private Long id;
    private String brandName;
    private String modelName;
    private int year;
    private String vin;
    private Double price;
}
