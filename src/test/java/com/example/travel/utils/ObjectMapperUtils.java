package com.example.travel.utils;

import lombok.experimental.UtilityClass;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.type.TypeFactory;

@UtilityClass
public class ObjectMapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static ObjectMapper getMapper() {
        return MAPPER;
    }

    public static TypeFactory getTypeFactory() {
        return MAPPER.getTypeFactory();
    }
}