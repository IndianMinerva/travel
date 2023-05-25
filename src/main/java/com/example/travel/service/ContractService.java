package com.example.travel.service;

import com.example.travel.dto.ContractCreationRequest;
import com.example.travel.dto.ContractDto;

import java.util.List;

public interface ContractService {
    List<ContractDto> getAllContracts();

    ContractDto getContractById(Long id);

    ContractDto updateContract(Long id, ContractCreationRequest contractCreationRequest);
    ContractDto createContract(ContractCreationRequest contractCreationRequest);
}
