package com.example.travel.mappers;

import com.example.travel.dto.VehicleDto;
import com.example.travel.model.Vehicle;

import java.util.Optional;

import static com.example.travel.mappers.ModelMapper.toModelDto;

public class VehicleMapper {
    public static VehicleDto toDto(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getId(),
                BrandMapper.toDto(vehicle.getBrand()),
                toModelDto(vehicle.getModel()),
                vehicle.getYear(),
                Optional.ofNullable(vehicle.getVin()).orElse("-"),
                vehicle.getPrice()
        );
    }
}
