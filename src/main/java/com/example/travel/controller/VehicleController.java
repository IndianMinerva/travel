package com.example.travel.controller;

import com.example.travel.dto.VehicleCreationRequest;
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
    public VehicleDto creteVehicle(@RequestBody VehicleCreationRequest vehicleCreationRequest) {
        return vehicleService.creteVehicle(vehicleCreationRequest);
    }

    @PutMapping("/{id}")
    public VehicleDto updateVehicle(@PathVariable Long id, @RequestBody VehicleCreationRequest vehicleCreationRequest) {
        return vehicleService.creteVehicle(vehicleCreationRequest);
    }

    @GetMapping("/{id}")
    public VehicleDto getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicle(id);
    }

    @GetMapping
    public List<VehicleDto> getVehicles() {
        return vehicleService.getAllVehicles();
    }
}
