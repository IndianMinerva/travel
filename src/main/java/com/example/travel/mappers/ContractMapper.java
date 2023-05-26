package com.example.travel.mappers;

import com.example.travel.dto.ContractDto;
import com.example.travel.model.Contract;

import java.util.stream.Collectors;

public class ContractMapper {
    public static ContractDto toDto(Contract leasingContract) {
        return ContractDto
                .builder()
                .contractNo(leasingContract.getId())
                .customer(CustomerMapper.toDto(leasingContract.getCustomer()))
                .vehicles(leasingContract.getVehicles().stream().map(VehicleMapper::toDto).collect(Collectors.toList()))
                .monthlyRate(leasingContract.getRate())
                .build();
    }
}
