package com.example.travel.service;

import com.example.travel.dto.ModelCreationRequest;
import com.example.travel.dto.ModelDto;
import com.example.travel.model.Model;

import java.util.List;

public interface ModelService {
    ModelDto createModel(ModelCreationRequest modelCreationRequest);

    public Model getOrCreateModel(String modelName);
    List<ModelDto> getAllModels();
}
