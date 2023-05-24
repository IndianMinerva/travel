package com.example.travel.service.impl;

import com.example.travel.dto.VehicleCreationRequest;
import com.example.travel.dto.VehicleDto;
import com.example.travel.exception.VehicleNotFoundException;
import com.example.travel.mappers.VehicleMapper;
import com.example.travel.model.Vehicle;
import com.example.travel.repository.VehicleRepository;
import com.example.travel.service.BrandService;
import com.example.travel.service.ModelService;
import com.example.travel.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;


    @Override
    public VehicleDto creteVehicle(VehicleCreationRequest vehicleCreationRequest) {
        var brand = brandService.getOrCreateBrand(vehicleCreationRequest.getBrandName());
        var model = modelService.getOrCreateModel(vehicleCreationRequest.getModelName());

        Vehicle.VehicleBuilder vehicleBuilder = Vehicle
                .builder()
                .brand(brand)
                .model(model)
                .year(vehicleCreationRequest.getYear())
                .vin(vehicleCreationRequest.getVin())
                .price(vehicleCreationRequest.getPrice());

        return saveVehicle(vehicleBuilder.build());
    }

    private VehicleDto saveVehicle(Vehicle vehicle) {
        return VehicleMapper.toVehicleDto(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleDto getVehicle(Long id) {
        return vehicleRepository
                .findById(id)
                .map(VehicleMapper::toVehicleDto)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with the id could not be found"));
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(VehicleMapper::toVehicleDto).collect(Collectors.toList());
    }
}
