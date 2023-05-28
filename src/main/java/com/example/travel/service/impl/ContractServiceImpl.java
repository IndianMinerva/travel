package com.example.travel.service.impl;

import com.example.travel.dto.ContractCreationUpdationRequest;
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

import static java.util.stream.Collectors.toList;

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
        return contractRepository.findAll().stream().map(ContractMapper::toDto).collect(toList());
    }
    
    @Override
    public ContractDto getContractById(Long id) {
        return ContractMapper.toDto(contractRepository
                .findById(id)
                .orElseThrow(() -> new ContractNotFoundException("Contract with the id " + id + " could not be found"))
        );
    }

    @Override
    @Transactional
    public ContractDto updateContract(Long id, ContractCreationUpdationRequest contractCreationUpdationRequest) {
        List<Vehicle> vehicles = getVehicles(contractCreationUpdationRequest);
        List<Vehicle> availableVehicles = getAvailableVehicles(vehicles, id);
        List<Vehicle> unavailableVehicles = getUnavailableVehicles(vehicles, id);

        Customer customer = customerRepository
                .findById(contractCreationUpdationRequest.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found. id: "
                        + contractCreationUpdationRequest.getCustomerId()));

        Contract contract = Contract
                .builder()
                .id(id)
                .rate(contractCreationUpdationRequest.getRate())
                .customer(customer)
                .vehicles(vehicles)
                .build();
        availableVehicles.forEach(vehicle -> vehicle.setContract(contract));

        ContractDto contractDto = ContractMapper
                .toDto(contractRepository.save(contract));
        contractDto.setUnavailableVehicles(unavailableVehicles);

        return contractDto;
    }

    @Override
    @Transactional
    public ContractDto createContract(ContractCreationUpdationRequest contractCreationUpdationRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(contractCreationUpdationRequest.getCustomerId());
        Customer customer = customerOptional.orElseThrow(() -> new CustomerNotFoundException("Customer with the Id "
                + contractCreationUpdationRequest.getCustomerId() + " could not be found"));

        List<Vehicle> vehicles = getVehicles(contractCreationUpdationRequest);
        List<Vehicle> availableVehicles = getAvailableVehicles(vehicles, null);
        List<Vehicle> unavailableVehicles = getUnavailableVehicles(vehicles, null);

        Contract contract = Contract.builder().customer(customer).vehicles(availableVehicles).rate(contractCreationUpdationRequest.getRate()).build();
        availableVehicles.forEach(vehicle -> vehicle.setContract(contract));
        ContractDto contractDto = ContractMapper.toDto(contractRepository.save(contract));
        contractDto.setUnavailableVehicles(unavailableVehicles);
        return contractDto;
    }

    private List<Vehicle> getVehicles(ContractCreationUpdationRequest contractCreationUpdationRequest) {
        return vehicleRepository.findAllById(contractCreationUpdationRequest.getVehicleIds());
    }

    private List<Vehicle> getAvailableVehicles(List<Vehicle> vehicles, Long contractId) {
        return vehicles.stream().filter(vehicle -> vehicle.getContract() == null || vehicle.getContract().getId() == contractId).collect(toList());
    }

    private List<Vehicle> getUnavailableVehicles(List<Vehicle> vehicles, Long contractId) {
        return vehicles.stream().filter(vehicle -> vehicle.getContract() != null && vehicle.getContract().getId() != contractId).collect(toList());
    }
}
