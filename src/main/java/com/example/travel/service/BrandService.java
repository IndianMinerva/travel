package com.example.travel.service;

import com.example.travel.dto.BrandCreationRequest;
import com.example.travel.dto.BrandDto;
import com.example.travel.model.Brand;

import java.util.List;

public interface BrandService {
    BrandDto createBrand(BrandCreationRequest brandCreationRequest);

    Brand getOrCreateBrand(String brandName);

    List<BrandDto> getAllBrands();
}
