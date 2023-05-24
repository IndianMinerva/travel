package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VehicleDTO {

    private Long id;
    private BrandDto brandDto;
    private ModelDto modelDto;
}
