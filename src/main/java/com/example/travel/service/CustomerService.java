package com.example.travel.service;

import com.example.travel.dto.CustomerCreationRequest;
import com.example.travel.dto.CustomerDto;
import com.example.travel.model.Customer;

import java.text.ParseException;
import java.util.List;

public interface CustomerService {
    CustomerDto createCustomer(CustomerCreationRequest customerCreationRequest) throws ParseException;

    public Customer getOrCreateCustomer(Customer customer);

    List<CustomerDto> getAllCustomers();
}
