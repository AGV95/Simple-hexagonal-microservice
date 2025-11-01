package com.hexagonal.animals.infrastructure.rest;

import com.hexagonal.animals.domain.model.Animal;
import com.hexagonal.animals.domain.ports.input.AnimalServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animales")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalServicePort animalServicePort;

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal animal) {
        return new ResponseEntity<>(animalServicePort.saveAnimal(animal), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable Long id) {
        return animalServicePort.getAnimalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalServicePort.getAllAnimals());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalServicePort.getAnimalById(id)
                .map(existingAnimal -> {
                    animal.setId(id);
                    return ResponseEntity.ok(animalServicePort.updateAnimal(animal));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        if (animalServicePort.getAnimalById(id).isPresent()) {
            animalServicePort.deleteAnimal(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
