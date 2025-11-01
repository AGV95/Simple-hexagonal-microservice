package com.hexagonal.animals.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    private Long id;
    private String nombre;
    private String especie;
    private int edad;
    private String habitat;
}
