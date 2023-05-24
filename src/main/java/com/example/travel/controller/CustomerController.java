package com.example.travel.controller;

import com.example.travel.dto.CustomerDto;
import com.example.travel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return customerService.createOrUpdateCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, CustomerDto customerDto) { //TODO: use DTO
            return customerService.createOrUpdateCustomer(id, customerDto);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @GetMapping
    public List<CustomerDto> getCustomers() {
        return customerService.getAllCustomers();
    }
}
