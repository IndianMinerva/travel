package com.example.travel.service.impl;

import com.example.travel.dto.VehicleCreationUpdationRequest;
import com.example.travel.exception.VehicleNotFoundException;
import com.example.travel.mappers.VehicleMapper;
import com.example.travel.model.Brand;
import com.example.travel.model.Model;
import com.example.travel.model.Vehicle;
import com.example.travel.repository.VehicleRepository;
import com.example.travel.service.BrandService;
import com.example.travel.service.ModelService;
import com.example.travel.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ModelService modelService;


    @Override
    public com.example.travel.dto.VehicleDto creteVehicle(VehicleCreationUpdationRequest vehicleCreationUpdationRequest) {
        return updateVehicle(null, vehicleCreationUpdationRequest);
    }

    @Override
    @Transactional
    public com.example.travel.dto.VehicleDto updateVehicle(Long id, VehicleCreationUpdationRequest vehicleCreationUpdationRequest) {
        Brand brand = brandService.getOrCreateBrand(vehicleCreationUpdationRequest.getBrandName());
        Model model = modelService.getOrCreateModel(vehicleCreationUpdationRequest.getModelName());
        Vehicle vehicle;
        if (id != null) {
            vehicle = vehicleRepository.findById(id).orElseGet(Vehicle::new);
        } else {
            vehicle = new Vehicle();
        }

        Vehicle.VehicleBuilder vehicleBuilder = Vehicle
                .builder()
                .id(vehicle.getId())
                .version(vehicle.getVersion())
                .brand(brand)
                .model(model)
                .model_year(vehicleCreationUpdationRequest.getYear())
                .vin(vehicleCreationUpdationRequest.getVin())
                .price(vehicleCreationUpdationRequest.getPrice());

        return saveVehicle(vehicleBuilder.build());
    }

    private com.example.travel.dto.VehicleDto saveVehicle(Vehicle vehicle) {
        return VehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    public com.example.travel.dto.VehicleDto getVehicle(Long id) {
        return vehicleRepository
                .findById(id)
                .map(VehicleMapper::toDto)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with the id " + id + " could not be found"));
    }

    @Override
    public List<com.example.travel.dto.VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(VehicleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<com.example.travel.dto.VehicleDto> getAvailableVehicles() {
        return StreamSupport
                .stream(vehicleRepository.findByContractNull().spliterator(), false)
                .map(VehicleMapper::toDto)
                .collect(Collectors.toList());
    }
}
