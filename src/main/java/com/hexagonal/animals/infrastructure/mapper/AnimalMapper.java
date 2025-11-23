package com.hexagonal.animals.infrastructure.mapper;

import com.hexagonal.animals.domain.model.Animal;
import com.hexagonal.animals.infrastructure.entity.AnimalEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public AnimalEntity toEntity(Animal animal) {
        return new AnimalEntity(animal.getName(), animal.getSpecies(),
                animal.getAge(), animal.getHabitat());
    }

    public Animal toDomain(AnimalEntity entity) {
        return new Animal(
                entity.getId(),
                entity.getName(),
                entity.getSpecies(),
                entity.getAge(),
                entity.getHabitat()
        );
    }
}
