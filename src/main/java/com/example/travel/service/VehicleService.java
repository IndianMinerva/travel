package com.example.travel.service;

import com.example.travel.dto.VehicleCreationRequest;
import com.example.travel.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto creteVehicle(VehicleCreationRequest vehicleCreationRequest);

    VehicleDto updateVehicle(Long Id, VehicleCreationRequest vehicleCreationRequest);

    VehicleDto getVehicle(Long id);

    List<VehicleDto> getAllVehicles(); //Need to pagenate


}
