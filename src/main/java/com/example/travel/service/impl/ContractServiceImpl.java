package com.example.travel.service.impl;

import com.example.travel.dto.ContractCreationRequest;
import com.example.travel.dto.ContractDto;
import com.example.travel.exception.ContractNotFoundException;
import com.example.travel.exception.CustomerNotFoundException;
import com.example.travel.mappers.ContractMapper;
import com.example.travel.model.Contract;
import com.example.travel.model.Customer;
import com.example.travel.model.Vehicle;
import com.example.travel.repository.ContractRepository;
import com.example.travel.repository.CustomerRepository;
import com.example.travel.repository.VehicleRepository;
import com.example.travel.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

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
        List<Vehicle> vehicles = getVehicles(contractCreationRequest);
        List<Vehicle> unavailable = getUnavailableVehicles(vehicles);

        Customer customer = customerRepository
                .findById(contractCreationRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found. id: " + contractCreationRequest.getCustomerId()));

        ContractDto contractDto = ContractMapper.toDto(Contract.builder().rate(contractCreationRequest.getRate()).customer(customer).build());
        contractDto.setUnavailableVehicles(unavailable);

        return contractDto;
    }

    @Override
    @Transactional
    public ContractDto createContract(ContractCreationRequest contractCreationRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(contractCreationRequest.getCustomerId());
        Customer customer = customerOptional.orElseThrow(() -> new CustomerNotFoundException("Unknown customer"));
        List<Vehicle> vehicles = getVehicles(contractCreationRequest);
        List<Vehicle> unavailable = getUnavailableVehicles(vehicles);
        Contract contract = Contract.builder().customer(customer).vehicles(vehicles).rate(contractCreationRequest.getRate()).build();
        ContractDto contractDto = ContractMapper.toDto(contractRepository.save(contract));
        contractDto.setUnavailableVehicles(unavailable);

        return ContractMapper.toDto(contract);
    }

    private List<Vehicle> getVehicles(ContractCreationRequest contractCreationRequest) {
        return vehicleRepository.findAllById(contractCreationRequest.getVehicleIds()).stream()
                .filter(vehicle -> vehicle.getContract() == null)
                .collect(Collectors.toList());
    }

    private List<Vehicle> getUnavailableVehicles(List<Vehicle> vehicles) {
        return vehicles
                .stream()
                .filter(vehicle -> vehicle.getContract() != null).collect(Collectors.toList());
    }
}
