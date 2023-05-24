package com.example.travel.mappers;

import com.example.travel.dto.ModelDto;
import com.example.travel.model.Model;

public class ModelMapper {
    public static ModelDto toModelDto(Model model) {
        return new ModelDto(model.getId(), model.getName());
    }

    public static Model toEntity(ModelDto modelDto) {
        return new Model(modelDto.getName());
    }
}
