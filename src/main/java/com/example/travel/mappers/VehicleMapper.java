package com.example.travel.mappers;

import com.example.travel.dto.VehicleDTO;
import com.example.travel.model.Vehicle;

import static com.example.travel.mappers.BrandMapper.toDto;
import static com.example.travel.mappers.ModelMapper.toModelDto;

public class VehicleMapper {
    public static VehicleDTO toVehicleDto(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                toDto(vehicle.getBrand()),
                toModelDto(vehicle.getModel()));
    }
}
