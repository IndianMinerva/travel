package com.example.travel.service;

import com.example.travel.dto.CustomerCreationRequest;
import com.example.travel.dto.CustomerDto;
import com.example.travel.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest
public class CustomerServiceTest {

    /*@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"))
            .withReuse(true);*/

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void beforeEach() {
        customerRepository.deleteAll();
    }

    @Test
    public void givenCustomers_should_returnCustomers() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //Given
        customerService.createCustomer(new CustomerCreationRequest("AAA", "BBB", dateFormat.parse("01.01.2000")));
        customerService.createCustomer(new CustomerCreationRequest("DEF", "GHI", dateFormat.parse("01.01.1990")));

        //when
        List<CustomerDto> customers = customerService.getAllCustomers();

        //then
        assertEquals(2, customers.size());
        customerRepository.deleteAll();
    }
}
