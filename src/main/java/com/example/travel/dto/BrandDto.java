package com.example.travel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Data
public class BrandDto {
    private Long id;
    private String name;

    public String getName() {
        return name.toUpperCase();
    }
}
