package com.hexagonal.animals.domain.ports.input;

import com.hexagonal.animals.domain.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalServicePort {
    Animal saveAnimal(Animal animal);

    Optional<Animal> getAnimalById(Long id);

    List<Animal> getAllAnimals();

    int updateAnimal(Animal animal);

    void deleteAnimal(Long id);
}
