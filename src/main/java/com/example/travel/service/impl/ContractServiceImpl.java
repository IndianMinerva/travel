package com.example.travel.service.impl;

import com.example.travel.dto.ContractCreationRequest;
import com.example.travel.dto.ContractDto;
import com.example.travel.exception.ContractNotFoundException;
import com.example.travel.exception.CustomerNotFoundException;
import com.example.travel.mappers.ContractMapper;
import com.example.travel.model.Contract;
import com.example.travel.model.Customer;
import com.example.travel.repository.ContractRepository;
import com.example.travel.repository.CustomerRepository;
import com.example.travel.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<ContractDto> getAllContracts() {
        return contractRepository.findAll().stream().map(ContractMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ContractDto getContractById(Long id) {
        return ContractMapper.toDto(contractRepository
                .findById(id)
                .orElseThrow(() -> new ContractNotFoundException("Contract could not be found"))
        );
    }

    @Override
    public ContractDto updateContract(Long id, ContractCreationRequest contractCreationRequest) {
        Customer customer = customerRepository
                .findById(contractCreationRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found. id: " + contractCreationRequest.getCustomerId()));

        return ContractMapper.toDto(Contract.builder().rate(contractCreationRequest.getRate()).customer(customer).build());
    }

    @Override
    public ContractDto createContract(ContractCreationRequest contractCreationRequest) {
        var customerOptional = customerRepository.findById(contractCreationRequest.getCustomerId());
        Customer customer = customerOptional.orElseThrow(() -> new CustomerNotFoundException("Unknown customer"));
        return ContractMapper.toDto(contractRepository
                .save(Contract
                        .builder()
                        .customer(customer)
                        .rate(contractCreationRequest.getRate()
                        )
                        .build()
                )
        );
    }
}
