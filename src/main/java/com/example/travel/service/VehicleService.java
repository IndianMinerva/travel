package com.example.travel.service;

import com.example.travel.dto.VehicleCreationRequest;
import com.example.travel.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {
    VehicleDTO creteVehicle(VehicleCreationRequest vehicleCreationRequest);


    VehicleDTO getVehicle(Long id);

    List<VehicleDTO> getAllVehicles(); //Need to pagenate


}
