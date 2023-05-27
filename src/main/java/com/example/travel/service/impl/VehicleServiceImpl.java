package com.example.travel.service.impl;

import com.example.travel.dto.VehicleCreationRequest;
import com.example.travel.dto.VehicleDto;
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
    public VehicleDto creteVehicle(VehicleCreationRequest vehicleCreationRequest) {
        return updateVehicle(null, vehicleCreationRequest);
    }

    @Override
    @Transactional
    public VehicleDto updateVehicle(Long id, VehicleCreationRequest vehicleCreationRequest) {
        Brand brand = brandService.getOrCreateBrand(vehicleCreationRequest.getBrandName());
        Model model = modelService.getOrCreateModel(vehicleCreationRequest.getModelName());
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
                .model_year(vehicleCreationRequest.getYear())
                .vin(vehicleCreationRequest.getVin())
                .price(vehicleCreationRequest.getPrice());

        return saveVehicle(vehicleBuilder.build());
    }

    private VehicleDto saveVehicle(Vehicle vehicle) {
        return VehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleDto getVehicle(Long id) {
        return vehicleRepository
                .findById(id)
                .map(VehicleMapper::toDto)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with the id could not be found"));
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream().map(VehicleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<VehicleDto> getAvailableVehicles() {
        return StreamSupport
                .stream(vehicleRepository.findByCustomerNull().spliterator(), false)
                .map(VehicleMapper::toDto)
                .collect(Collectors.toList());
    }
}
