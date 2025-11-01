package com.hexagonal.animals.infrastructure.mapper;

import com.hexagonal.animals.domain.model.Animal;
import com.hexagonal.animals.infrastructure.entity.AnimalEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public AnimalEntity toEntity(Animal animal) {
        return AnimalEntity.builder()
            .id(animal.getId())
            .nombre(animal.getNombre())
            .especie(animal.getEspecie())
            .edad(animal.getEdad())
            .build();
    }

    public Animal toDomain(AnimalEntity entity) {
        return new Animal(
            entity.getId(),
            entity.getNombre(),
            entity.getEspecie(),
            entity.getEdad()
        );
    }
}
