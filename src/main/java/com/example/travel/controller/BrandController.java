package com.example.travel.controller;

import com.example.travel.dto.BrandCreationRequest;
import com.example.travel.dto.BrandDto;
import com.example.travel.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping
    public BrandDto createBrand(@RequestBody @Validated BrandCreationRequest brandCreationRequest) {
        return brandService.createBrand(brandCreationRequest);
    }

    @GetMapping
    public List<BrandDto> getBrands() {
        return brandService.getAllBrands();
    }
}
