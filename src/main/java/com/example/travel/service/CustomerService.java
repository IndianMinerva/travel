package com.example.travel.service;

import com.example.travel.dto.CustomerCreationRequest;
import com.example.travel.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomer(Long id);

    CustomerDto createCustomer(CustomerCreationRequest customerCreationRequest);

    CustomerDto updateCustomer(Long id, CustomerCreationRequest customerCreationRequest);

    List<CustomerDto> getAllCustomers();
}
