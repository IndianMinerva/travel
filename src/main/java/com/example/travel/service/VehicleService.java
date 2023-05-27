package com.example.travel.service;

import com.example.travel.dto.VehicleCreationUpdationRequest;
import com.example.travel.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto creteVehicle(VehicleCreationUpdationRequest vehicleCreationUpdationRequest);

    VehicleDto updateVehicle(Long Id, VehicleCreationUpdationRequest vehicleCreationUpdationRequest);

    List<VehicleDto> getAvailableVehicles();

    VehicleDto getVehicle(Long id);

    List<VehicleDto> getAllVehicles(); //Need to pagenate


}
