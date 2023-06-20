package com.example.travel.controller;

import com.example.travel.dto.BrandCreationRequest;
import com.example.travel.dto.BrandDto;
import com.example.travel.service.BrandService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = false, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token") })
    public List<BrandDto> getBrands(@RequestHeader(name = "Authorization") String token) {
        System.out.println("Token " + token);
        return brandService.getAllBrands();
    }
}
