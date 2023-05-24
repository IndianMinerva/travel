package com.example.travel.mappers;

import com.example.travel.dto.BrandDto;
import com.example.travel.model.Brand;

public class BrandMapper{
    public static BrandDto toDto(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName());
    }

    public static Brand toEntity(BrandDto brandDTO) {
        return new Brand(brandDTO.getName());
    }
}
