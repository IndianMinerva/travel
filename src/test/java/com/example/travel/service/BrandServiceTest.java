package com.example.travel.service;

import com.example.travel.repository.BrandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration
public class BrandServiceTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void test() {
        System.out.println(brandRepository.count());
    }

    @Test
    public void test1() {
        System.out.println(brandRepository.count());
    }
}
