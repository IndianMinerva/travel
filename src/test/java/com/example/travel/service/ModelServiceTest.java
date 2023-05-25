package com.example.travel.service;

import com.example.travel.dto.ModelDto;
import com.example.travel.repository.ModelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest
public class ModelServiceTest {

    @Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"))
            .withReuse(true);
    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelRepository modelRepository;

    @Test
    public void givenModels_should_returnTheModels() {
        //Given
        modelService.getOrCreateModel("S1");
        modelService.getOrCreateModel("S42");

        //when
        List<ModelDto> brands = modelService.getAllModels();

        //then
        assertEquals(2, brands.size());
        Assertions.assertTrue(brands.stream().map(ModelDto::getName).collect(Collectors.toList()).containsAll(List.of("S1", "S42")));
        modelRepository.deleteAll();
    }

    @Test
    public void givenBrandNotExist_should_createBrand() {
        //Given
        assertEquals(modelRepository.findByName("X92"), Optional.empty());
        modelService.getOrCreateModel("X92");
        modelService.getOrCreateModel("X92");

        //when
        List<ModelDto> models = modelService.getAllModels();

        //then
        assertEquals(1, models.size());
        Assertions.assertTrue(models.stream().map(ModelDto::getName).collect(Collectors.toList()).contains("X92"));
        modelRepository.deleteAll();
    }
}
