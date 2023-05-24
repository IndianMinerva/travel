package com.example.travel.mappers;

import com.example.travel.dto.CustomerDto;
import com.example.travel.model.Customer;

public class CustomerMapper {
    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getDateOfBirth());
    }

    public static Customer toEntity(CustomerDto customerDto) {
        return new Customer(customerDto.getId(), customerDto.getFirstName(), customerDto.getLastName(), customerDto.getDob());
    }
}
