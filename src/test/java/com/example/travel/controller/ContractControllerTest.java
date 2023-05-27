package com.example.travel.controller;

import com.example.travel.TravelApplication;
import com.example.travel.dto.*;
import com.example.travel.service.ContractService;
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
public class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;


    @Test
    public void given_contractCreationUpdatingRequest_shouldCreateAndReturn_OK() throws Exception {
        //Given
        ContractCreationUpdationRequest contractCreationUpdationRequest = new ContractCreationUpdationRequest(123L, 123.45d, List.of(1L, 2L));
        List<VehicleDto> vehicleDtos = List.of(
                new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X3456", 10000.00d),
                new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X3456", 10000.00d)
        );
        ContractDto expectedContractDto = new ContractDto(123L,
                new CustomerDto(1L, "firstName", "lastName", new Date()),
                vehicleDtos, 123.50d, List.of()
        );

        Mockito.when(contractService.createContract(any())).thenReturn(expectedContractDto);

        //When
        String jsonString = this.mockMvc
                .perform(post("/contracts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(contractCreationUpdationRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        ContractDto contractDto = getMapper().readValue(jsonString, ContractDto.class);

        //then
        Assert.assertEquals(expectedContractDto, contractDto);
    }

    @Test
    public void given_contract_when_updateContract_shouldReturnUpdatedContract() throws Exception {
        //Given
        ContractCreationUpdationRequest contractCreationUpdationRequest = new ContractCreationUpdationRequest(123L, 123.45d, List.of(1L, 2L));
        List<VehicleDto> vehicleDtos = List.of(
                new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X3456", 10000.00d),
                new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X3456", 10000.00d)
        );
        ContractDto expectedContractDto = new ContractDto(123L,
                new CustomerDto(1L, "firstName", "lastName", new Date()),
                vehicleDtos, 123.50d, List.of()
        );

        Mockito.when(contractService.updateContract(any(), any())).thenReturn(expectedContractDto);

        //When
        String jsonString = this.mockMvc
                .perform(put("/contracts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getMapper().writeValueAsString(contractCreationUpdationRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();

        ContractDto contractDto = getMapper().readValue(jsonString, ContractDto.class);

        //then
        Assert.assertEquals(expectedContractDto, contractDto);
    }

   @Test
    public void given_contracts_getAllContracts_should_returnAllContracts() throws Exception {
       //Given
       ContractCreationUpdationRequest contractCreationUpdationRequest = new ContractCreationUpdationRequest(123L, 123.45d, List.of(1L, 2L));
       List<VehicleDto> vehicleDtos = List.of(
               new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X3456", 10000.00d),
               new VehicleDto(1L, new BrandDto(1L, "BMW"), new ModelDto(1L, "S42"), 2000, "X3456", 10000.00d)
       );
       List<ContractDto> expectedContractDtos =  List.of(new ContractDto(123L,
               new CustomerDto(1L, "firstName", "lastName", new Date()),
               vehicleDtos, 123.50d, List.of()
       ));

       Mockito.when(contractService.getAllContracts()).thenReturn(expectedContractDtos);

       //When
       String jsonString = this.mockMvc
               .perform(get("/contracts")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(getMapper().writeValueAsString(contractCreationUpdationRequest)))
               .andExpect(status().isOk())
               .andReturn().getResponse()
               .getContentAsString();

       List<ContractDto> contractDtos = Arrays.asList(getMapper().readValue(jsonString, ContractDto[].class));

       //then
       Assert.assertEquals(expectedContractDtos, contractDtos);
   }
}
