package com.example.travel.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ContractCreationUpdationRequest {
    private Long customerId;
    @Min(value = 0, message = "Rate cannot be less than =")
    private Double rate;
    private List<Long> vehicleIds;
}
