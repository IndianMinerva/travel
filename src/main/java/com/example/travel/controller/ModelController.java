package com.example.travel.controller;

import com.example.travel.dto.ModelCreationRequest;
import com.example.travel.dto.ModelDto;
import com.example.travel.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @PostMapping
    public ModelDto createModel(@RequestBody ModelCreationRequest modelCreationRequest) {
        return modelService.createModel(modelCreationRequest);
    }

    @GetMapping
    public List<ModelDto> getModels() {
        return modelService.getAllModels();
    }
}
