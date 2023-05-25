package com.example.travel.controller;

import com.example.travel.dto.ContractCreationRequest;
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
    public ContractDto updateContract(@PathVariable Long id, @RequestBody ContractCreationRequest contractCreationRequest) {
        return contractService.updateContract(id, contractCreationRequest);
    }

    @PostMapping
    public ContractDto createContract(@RequestBody ContractCreationRequest contractCreationRequest) {
        return contractService.createContract(contractCreationRequest);
    }
}
