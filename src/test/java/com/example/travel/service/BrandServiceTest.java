package com.example.travel.service;

import com.example.travel.dto.BrandDto;
import com.example.travel.repository.BrandRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@SpringBootTest
public class BrandServiceTest {

//    @Container
//    public static PostgreSQLContainer mySQLContainer = (PostgreSQLContainer) new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
//            .withReuse(true);
    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @BeforeEach
    @AfterEach
    public void cleanUp() {
        brandRepository.deleteAll();
    }

    @Test
    public void givenBrands_should_returnTheBrands() {
        //Given
        brandService.getOrCreateBrand("BMW");
        brandService.getOrCreateBrand("Audi");

        //when
        List<BrandDto> brands = brandService.getAllBrands();

        //then
        assertEquals(2, brands.size());
        Assertions.assertTrue(brands.stream().map(BrandDto::getName).collect(Collectors.toList()).containsAll(List.of("BMW", "AUDI")));
        brandRepository.deleteAll();
    }

    @Test
    public void givenBrandNotExist_should_createBrand() {
        //Given
        assertEquals(brandRepository.findByName("BMW"), Optional.empty());
        brandService.getOrCreateBrand("BMW");
        brandService.getOrCreateBrand("BMW");

        //when
        List<BrandDto> brands = brandService.getAllBrands();

        //then
        assertEquals(1, brands.size());
        Assertions.assertTrue(brands.stream().map(BrandDto::getName).collect(Collectors.toList()).contains("BMW"));
        brandRepository.deleteAll();
    }
}
