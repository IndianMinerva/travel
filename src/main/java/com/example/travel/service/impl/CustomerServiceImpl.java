package com.example.travel.service.impl;

import com.example.travel.dto.CustomerCreationRequest;
import com.example.travel.dto.CustomerDto;
import com.example.travel.mappers.CustomerMapper;
import com.example.travel.model.Customer;
import com.example.travel.repository.CustomerRepository;
import com.example.travel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy.MM.dd");
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerCreationRequest customerCreationRequest) throws ParseException {
        Customer customer = new Customer(
                customerCreationRequest.getId(),
                customerCreationRequest.getFirstName(),
                customerCreationRequest.getLastName(),
                FORMATTER.parse(customerCreationRequest.getDob()));  //TODO: Handle exception also validate this at the request
        return CustomerMapper.toDto(getOrCreateCustomer(customer));
    }

    @Override
    public Customer getOrCreateCustomer(Customer customer) {
        return customer;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toDto).collect(Collectors.toList());
    }
}
