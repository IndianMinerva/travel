package com.example.travel.service.impl;

import com.example.travel.dto.ModelCreationRequest;
import com.example.travel.dto.ModelDto;
import com.example.travel.mappers.ModelMapper;
import com.example.travel.model.Model;
import com.example.travel.repository.ModelRepository;
import com.example.travel.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Override
    public ModelDto createModel(ModelCreationRequest modelCreationRequest) {
        return ModelMapper.toModelDto(getOrCreateModel(modelCreationRequest.getName()));
    }

    @Override
    public Model getOrCreateModel(String modelName) {
        return modelRepository
                .findByName(modelName)
                .orElseGet(() -> modelRepository.save(new Model(modelName)));
    }

    @Override
    public List<ModelDto> getAllModels() {
        return modelRepository
                .findAll()
                .stream()
                .map(ModelMapper::toModelDto)
                .collect(Collectors.toList());
    }
}
