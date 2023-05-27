package com.example.travel.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContractCreationUpdationRequest {
    private Long customerId;
    private Double rate;
    private List<Long> vehicleIds;
}
