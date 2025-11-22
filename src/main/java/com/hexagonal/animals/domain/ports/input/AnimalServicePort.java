package com.hexagonal.animals.domain.ports.input;

import com.hexagonal.animals.domain.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalServicePort {
    Animal saveAnimal(Animal animal);

    Optional<Animal> getAnimalById(Long id);

    List<Animal> getAllAnimals();

    /**
     * Actualiza un animal existente. Devuelve 1 si la actualización fue exitosa, -1 si hubo un error durante la actualización,
     * o 0 si el animal con el ID proporcionado no existe.
     *
     * @param animal Animal a actualizar.
     * @param id     ID del animal a actualizar.
     * @return int Resultado de la operación de actualización.
     */
    int updateAnimal(Animal animal, Long id);

    void deleteAnimal(Long id);
}
