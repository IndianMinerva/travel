package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleDto {

    private Long id;
    private BrandDto brandDto;
    private ModelDto modelDto;
    private int year;
    private String vin;
    private Double price;  //TODO: Adjust it to two decimal points
}
