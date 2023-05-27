package com.example.travel.service;

import com.example.travel.dto.CustomerCreationUpdationRequest;
import com.example.travel.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomer(Long id);

    CustomerDto createCustomer(CustomerCreationUpdationRequest customerCreationUpdationRequest);

    CustomerDto updateCustomer(Long id, CustomerCreationUpdationRequest customerCreationUpdationRequest);

    List<CustomerDto> getAllCustomers();
}
