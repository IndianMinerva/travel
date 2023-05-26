package com.example.travel.service;

import com.example.travel.dto.*;
import com.example.travel.repository.BrandRepository;
import com.example.travel.repository.ModelRepository;
import com.example.travel.repository.VehicleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest
public class ContractServiceTest {

    /*@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"))
            .withUsername("root")
            .withPassword("password")
            .withDatabaseName("travel")
            .withReuse(true);*/
    @Autowired
    private ContractService contractService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;


    @AfterEach
    public void afterEach() {
        vehicleRepository.deleteAll();
        brandRepository.deleteAll();
        modelRepository.deleteAll();
    }

    @Test
    public void givenVehicles_should_returnVehicles() {
        //Given
        VehicleDto vehicle1 = vehicleService.creteVehicle(new VehicleCreationRequest("BMW", "S1", 2000, "X12345", 123.45d));
        VehicleDto vehicle2 = vehicleService.creteVehicle(new VehicleCreationRequest("AUDI", "X12", 2000, null, 123.45d));
        CustomerDto customerDto = customerService.createCustomer(new CustomerCreationRequest("Mark", "Twain", new Date()));
        //when
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        ContractDto contractDto = contractService.createContract(new ContractCreationRequest(customerDto.getId(), 23.50d, List.of(vehicle1.getId(), vehicle2.getId())));
        //then
        assertEquals(vehicles.size(), contractDto.getVehicles().size());
        vehicles.stream().filter(v -> !Objects.isNull(v.getVin())).findFirst().map(v -> {
            Assertions.assertEquals(vehicle1, v);
            return true;
        });

        vehicles.stream().filter(v -> Objects.isNull(v.getVin())).findFirst().map(v -> {
            Assertions.assertEquals(vehicle2, v);
            return true;
        });
    }

    @Test
    public void givenVehicles_should_returnParticularVehicle() {
        //Given
        VehicleDto vehicle1 = vehicleService.creteVehicle(new VehicleCreationRequest("BMW", "S1", 2000, "X12345", 123.45d));
        vehicleService.creteVehicle(new VehicleCreationRequest("AUDI", "X12", 2000, null, 123.45d));

        //When
        VehicleDto vehicleDto = vehicleService.getVehicle(vehicle1.getId());

        //Then
        Assertions.assertEquals(vehicle1, vehicleDto);
    }

    @Test
    public void givenVehicle_should_allowUpdatingVehicle() {
        //Given
        VehicleDto vehicle1 = vehicleService.creteVehicle(new VehicleCreationRequest("BMW", "S1", 2000, null, 123000.45d));

        //when
        VehicleDto updatedVehicleDto = vehicleService.updateVehicle(vehicle1.getId(), new VehicleCreationRequest("BMW", "S1", 2000, "X880997", 224000.89d));
        VehicleDto retrievedVehicleDto = vehicleService.getVehicle(vehicle1.getId());

        //then
        Assertions.assertEquals(updatedVehicleDto, retrievedVehicleDto);

    }
}
