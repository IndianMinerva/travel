package com.example.travel.controller;

import com.example.travel.dto.ContractCreationUpdationRequest;
import com.example.travel.dto.ContractDto;
import com.example.travel.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    @Autowired
    private ContractService contractService;

    @GetMapping
    public List<ContractDto> getAllContracts() {
        return contractService.getAllContracts();
    }

    @GetMapping("/{id}")
    public ContractDto getContractById(@PathVariable Long id) {
        return contractService.getContractById(id);
    }

    @PutMapping("/{id}")
    public ContractDto updateContract(@PathVariable Long id, @RequestBody ContractCreationUpdationRequest contractCreationUpdationRequest) {
        return contractService.updateContract(id, contractCreationUpdationRequest);
    }

    @PostMapping
    public ContractDto createContract(@RequestBody ContractCreationUpdationRequest contractCreationUpdationRequest) {
        return contractService.createContract(contractCreationUpdationRequest);
    }
}
