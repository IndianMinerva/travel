package com.example.travel.service;

import com.example.travel.dto.*;
import com.example.travel.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@Testcontainers
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
    private ContractRepository contractRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;


    @BeforeEach
    @AfterEach
    public void cleanUp() {
        vehicleRepository.deleteAll();
        contractRepository.deleteAll();
        brandRepository.deleteAll();
        modelRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void givenContract_should_returnContract() {
        //Given
        VehicleDto vehicle1 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("BMW", "S1", 2000, "X12345", 123.45d));
        VehicleDto vehicle2 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("AUDI", "X12", 2000, null, 123.45d));
        CustomerDto customerDto = customerService.createCustomer(new CustomerCreationUpdationRequest("Mark", "Twain", new Date()));
        //when
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        ContractDto contractDto = contractService.createContract(new ContractCreationUpdationRequest(customerDto.getId(), 23.50d, List.of(vehicle1.getId(), vehicle2.getId())));
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

//    @Test
    public void givenContract_should_allowUpdation() {
        //Given
        VehicleDto vehicle1 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("BMW", "S1", 2000, "X12345", 123.45d));
        CustomerDto customerDto = customerService.createCustomer(new CustomerCreationUpdationRequest("Mark", "Twain", new Date()));
        //when
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();
        ContractDto contractDto = contractService.createContract(new ContractCreationUpdationRequest(customerDto.getId(), 23.50d, List.of(vehicle1.getId())));
        assertEquals(1, contractDto.getVehicles().size());

        VehicleDto vehicle2 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("AUDI", "X12", 2000, null, 123.45d));

        contractDto = contractService.updateContract(contractDto.getContractNo(), new ContractCreationUpdationRequest(customerDto.getId(), 23.50d, List.of(vehicle1.getId(), vehicle2.getId())));

        //then
        assertEquals(2, contractDto.getVehicles().size());
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
    public void givenVehiclePartOfAContract_should_NotBeAvailableForAnotherContract() {
        //Given
        VehicleDto vehicle1 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("BMW", "S1", 2000, "X12345", 123.45d));
        VehicleDto vehicle2 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("AUDI", "X12", 2000, null, 153.45d));
        VehicleDto vehicle3 = vehicleService.creteVehicle(new VehicleCreationUpdationRequest("Mercedes", "S55", 2020, "Y28392", 153.45d));
        CustomerDto customerDto = customerService.createCustomer(new CustomerCreationUpdationRequest("Mark", "Twain", new Date()));
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();

        //when
        ContractDto contractDto1 = contractService.createContract(new ContractCreationUpdationRequest(customerDto.getId(), 23.50d, List.of(vehicle1.getId(), vehicle2.getId())));
        ContractDto contractDto2 = contractService.createContract(new ContractCreationUpdationRequest(customerDto.getId(), 23.50d, List.of(vehicle2.getId(), vehicle3.getId())));

        //then
        assertEquals(2, contractDto1.getVehicles().size());
        assertEquals(1, contractDto2.getVehicles().size());
        assertEquals(1, contractDto2.getUnavailableVehicles().size());
        assertEquals(vehicle2.getId(), contractDto2.getUnavailableVehicles().get(0).getId());
    }
}
