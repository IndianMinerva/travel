package com.example.travel.controller;

import com.example.travel.TravelApplication;
import com.example.travel.dto.CustomerCreationRequest;
import com.example.travel.dto.CustomerDto;
import com.example.travel.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.example.travel.utils.ObjectMapperUtils.getMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TravelApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;


    @Test
    public void given_customer_shouldCreateAndReturn_OK() throws Exception {
        //Given
        CustomerCreationRequest customerCreationRequest = new CustomerCreationRequest("Wolfgang", "Pauli", new Date());
        CustomerDto expectedCustomerDto = new CustomerDto(1L, "Wolfgang", "Pauli", new Date());
        Mockito.when(customerService.createCustomer(any())).thenReturn(expectedCustomerDto);

        //When
        String jsonString = this.mockMvc
                .perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(customerCreationRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        CustomerDto customerDto = getMapper().readValue(jsonString, CustomerDto.class);

        //then
        Assert.assertEquals(expectedCustomerDto, customerDto);
    }

    @Test
    public void given_customers_when_getAllCustomers_shouldReturnCustomers() throws Exception {
        customerService.createCustomer(new CustomerCreationRequest("Wolfgang", "Pauli", new Date()));
        customerService.createCustomer(new CustomerCreationRequest("Albert", "Einstein", new Date()));
        List<CustomerDto> expectedCustomerDtos = List.of(new CustomerDto(1L, "Wolfgang", "Pauli", new Date()),
                new CustomerDto(2L, "Wolfgang", "Pauli", new Date()));
        Mockito.when(customerService.getAllCustomers()).thenReturn(expectedCustomerDtos);

        //When
        String jsonString = this.mockMvc
                .perform(get("/customers"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        List<CustomerDto> customerDtos = Arrays.asList(getMapper().readValue(jsonString, CustomerDto[].class));

        //then
        Assert.assertEquals(expectedCustomerDtos, customerDtos);
    }

    @Test
    public void given_customer_updateCustomer_should_updateCustomer() throws Exception {
        CustomerCreationRequest customerCreationRequest = new CustomerCreationRequest("Wolfgang", "Pauli", new Date());
        CustomerDto expectedCustomerDto = new CustomerDto(1L, "Richard", "Feynman", new Date());
        Mockito.when(customerService.updateCustomer(any(), any())).thenReturn(expectedCustomerDto);

        String jsonString = this.mockMvc
                .perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(customerCreationRequest))
                )
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        CustomerDto customerDto = getMapper().readValue(jsonString, CustomerDto.class);

        //then
        Assert.assertEquals(expectedCustomerDto, customerDto);
    }
}
