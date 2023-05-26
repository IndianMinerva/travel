package com.example.travel.service;

import com.example.travel.dto.VehicleCreationRequest;
import com.example.travel.dto.VehicleDto;
import com.example.travel.model.Brand;
import com.example.travel.model.Model;
import com.example.travel.model.Vehicle;
import com.example.travel.repository.BrandRepository;
import com.example.travel.repository.ModelRepository;
import com.example.travel.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest
public class VehicleServiceTest {

    /*@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"))
            .withUsername("root")
            .withPassword("password")
            .withDatabaseName("travel")
            .withReuse(true);*/
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ModelRepository modelRepository;


    @Test
    public void givenVehicles_should_returnVehicles() throws ParseException {
        //Given
        //Brand brand = brandRepository.save(new Brand("BMW"));
        //Model model = modelRepository.save(new Model("S42"));
        //vehicleRepository.save(new Vehicle(1L, 1L, brand, model, null, 2000, "X12345", 123.45d));
        vehicleService.creteVehicle(new VehicleCreationRequest("AUDI", "X12", 2000, null, 123.45d));

        //when
        List<VehicleDto> vehicles = vehicleService.getAllVehicles();

        //then
        assertEquals(1, vehicles.size());
        vehicleRepository.deleteAll();
        brandRepository.deleteAll();
        modelRepository.deleteAll();
    }
}
