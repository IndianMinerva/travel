package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class ModelDto {
    private Long id;

    @NotEmpty(message = "Model name cannot be null or empty")
    private String name;
}
