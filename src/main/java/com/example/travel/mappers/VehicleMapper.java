package com.example.travel.mappers;

import com.example.travel.model.Vehicle;

import static com.example.travel.mappers.ModelMapper.toModelDto;

public class VehicleMapper {
    public static com.example.travel.dto.VehicleDto toDto(Vehicle vehicle) {
        return new com.example.travel.dto.VehicleDto(
                vehicle.getId(),
                BrandMapper.toDto(vehicle.getBrand()),
                toModelDto(vehicle.getModel()),
                vehicle.getModel_year(),
                vehicle.getVin(),
                vehicle.getPrice()
        );
    }
}
