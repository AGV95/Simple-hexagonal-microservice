package com.hexagonal.animals.domain.ports.output;

import com.hexagonal.animals.domain.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepositoryPort {
    Animal save(Animal animal);

    Optional<Animal> findById(Long id);

    List<Animal> findAll();

    void deleteById(Long id);

    int update(Animal animal);
}
