package com.hexagonal.animals.domain.service;

import com.hexagonal.animals.domain.model.Animal;
import com.hexagonal.animals.domain.ports.input.AnimalServicePort;
import com.hexagonal.animals.domain.ports.output.AnimalRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService implements AnimalServicePort {

    private final AnimalRepositoryPort animalRepositoryPort;

    @Override
    public Animal saveAnimal(Animal animal) {
        return animalRepositoryPort.save(animal);
    }

    @Override
    public Optional<Animal> getAnimalById(Long id) {
        return animalRepositoryPort.findById(id);
    }

    @Override
    public List<Animal> getAllAnimals() {
        return animalRepositoryPort.findAll();
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        return animalRepositoryPort.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        animalRepositoryPort.deleteById(id);
    }
}
