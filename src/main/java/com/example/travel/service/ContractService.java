package com.example.travel.service;

import com.example.travel.dto.ContractCreationUpdationRequest;
import com.example.travel.dto.ContractDto;

import java.util.List;

public interface ContractService {
    List<ContractDto> getAllContracts();

    ContractDto getContractById(Long id);

    ContractDto updateContract(Long id, ContractCreationUpdationRequest contractCreationUpdationRequest);
    ContractDto createContract(ContractCreationUpdationRequest contractCreationUpdationRequest);
}
