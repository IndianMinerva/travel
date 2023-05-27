package com.example.travel.controller;

import com.example.travel.dto.VehicleCreationUpdationRequest;
import com.example.travel.dto.VehicleDto;
import com.example.travel.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public VehicleDto creteVehicle(@RequestBody VehicleCreationUpdationRequest vehicleCreationUpdationRequest) {
        return vehicleService.creteVehicle(vehicleCreationUpdationRequest);
    }

    @PutMapping("/{id}")
    public VehicleDto updateVehicle(@PathVariable Long id, @RequestBody VehicleCreationUpdationRequest vehicleCreationUpdationRequest) {
        return vehicleService.updateVehicle(id, vehicleCreationUpdationRequest);
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @GetMapping
    public List<VehicleDto> getVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/available")
    public List<VehicleDto> getAvailableVehicles() {
        return vehicleService.getAvailableVehicles();
    }
}
