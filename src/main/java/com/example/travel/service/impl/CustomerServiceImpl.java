package com.example.travel.service.impl;

import com.example.travel.dto.CustomerDto;
import com.example.travel.exception.CustomerNotFoundException;
import com.example.travel.exception.DateFormatException;
import com.example.travel.mappers.CustomerMapper;
import com.example.travel.model.Customer;
import com.example.travel.repository.CustomerRepository;
import com.example.travel.service.CustomerService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
public class CustomerServiceImpl implements CustomerService {
    private static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy.MM.dd");
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomer(Long id) {
        return customerRepository
                .findById(id)
                .map(CustomerMapper::toDto)
                .orElseThrow(() -> new CustomerNotFoundException("Given customer wuth the Id: " + id + " could not be found"));
    }

    @Override
    public CustomerDto createOrUpdateCustomer(CustomerDto customerDto) {
        return createOrUpdateCustomer(null, customerDto);
    }

    @Override
    public CustomerDto createOrUpdateCustomer(Long id, CustomerDto customerDto) {  //TODO: Finish this tomorrow
        final Date dob;
        try {
            dob = FORMATTER.parse(customerDto.getDob());
        } catch (ParseException pe) {
            throw new DateFormatException("Invalid date format: " + customerDto.getDob());
        }

        return customerRepository.findById(id).map(customer -> {
            Customer savedCustomer = new Customer(
                    customerDto.getId(),
                    customerDto.getFirstName(),
                    customerDto.getLastName(),
                    dob);  //TODO: validate this at the request
            return CustomerMapper.toDto(customerRepository.save(savedCustomer));
        }).orElseThrow(() -> new CustomerNotFoundException("Given customer wuth the Id: " + id + " could not be found"));
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toDto).collect(Collectors.toList());
    }
}
