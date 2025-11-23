package com.hexagonal.animals.infrastructure.adapter;

import com.hexagonal.animals.domain.model.Animal;
import com.hexagonal.animals.domain.ports.output.AnimalRepositoryPort;
import com.hexagonal.animals.infrastructure.entity.AnimalEntity;
import com.hexagonal.animals.infrastructure.mapper.AnimalMapper;
import com.hexagonal.animals.infrastructure.repository.AnimalJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AnimalRepositoryAdapter implements AnimalRepositoryPort {

    private final AnimalJpaRepository animalJpaRepository;
    private final AnimalMapper animalMapper;

    public AnimalRepositoryAdapter(AnimalJpaRepository animalJpaRepository,
                                   AnimalMapper animalMapper) {
        this.animalJpaRepository = animalJpaRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    public Animal save(Animal animal) {
        AnimalEntity animalEntity = animalMapper.toEntity(animal);
        AnimalEntity savedEntity = animalJpaRepository.save(animalEntity);
        return animalMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return animalJpaRepository.findById(id)
                .map(animalMapper::toDomain);
    }

    @Override
    public List<Animal> findAll() {
        return animalJpaRepository.findAll().stream()
                .map(animalMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        animalJpaRepository.deleteById(id);
    }

    @Override
    public int update(Animal animal) {
        return animalJpaRepository.update(
                animal.getId(),
                animal.getName(),
                animal.getSpecies(),
                animal.getAge()
        );

    }
}
