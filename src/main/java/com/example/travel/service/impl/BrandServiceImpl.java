package com.example.travel.service.impl;

import com.example.travel.dto.BrandCreationRequest;
import com.example.travel.dto.BrandDto;
import com.example.travel.mappers.BrandMapper;
import com.example.travel.model.Brand;
import com.example.travel.repository.BrandRepository;
import com.example.travel.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandDto createBrand(BrandCreationRequest brandCreationRequest) {
        return BrandMapper.toDto(getOrCreateBrand(brandCreationRequest.getName()));
    }

    @Override
    public Brand getOrCreateBrand(String brandName) {
        return brandRepository
                .findByName(brandName)
                .orElseGet(() -> brandRepository.save(new Brand(brandName)));
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository
                .findAll()
                .stream()
                .map(BrandMapper::toDto)
                .collect(Collectors.toList());
    }
}
