package com.example.travel.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ContractDto {
    private Long contractNo;
    private CustomerDto customer;
    private List<VehicleDto> vehicles;
    private Double monthlyRate;
}