package com.example.travel.service;

import com.example.travel.dto.BrandDto;
import com.example.travel.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@Testcontainers
@SpringBootTest
public class BrandServiceTest {

    /*@Container
    public static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"))
            .withReuse(true);*/
    @Autowired
    private BrandService brandService;

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void givenBrands_should_returnTheBrands() {
        //Given
        brandService.getOrCreateBrand("BMW");
        brandService.getOrCreateBrand("Audi");

        //when
        List<BrandDto> brands = brandService.getAllBrands();

        //then
        assertEquals(2, brands.size());
        Assertions.assertTrue(brands.stream().map(BrandDto::getName).collect(Collectors.toList()).containsAll(List.of("BMW", "Audi")));
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
