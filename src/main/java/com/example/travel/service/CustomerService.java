package com.example.travel.service;

import com.example.travel.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto getCustomer(Long id);

    CustomerDto createOrUpdateCustomer(CustomerDto customerDto);

    CustomerDto createOrUpdateCustomer(Long id, CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();
}
