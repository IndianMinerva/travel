package com.example.travel.dto;


import com.example.travel.model.Vehicle;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Data
public class ContractDto {
    private Long contractNo;
    private CustomerDto customer;
    private List<VehicleDto> vehicles;
    private Double monthlyRate;

    @Setter
    private List<Vehicle> unavailableVehicles;
}