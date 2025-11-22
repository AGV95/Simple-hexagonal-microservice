package com.hexagonal.animals.infrastructure.rest;

import com.hexagonal.animals.domain.model.Animal;
import com.hexagonal.animals.domain.ports.input.AnimalServicePort;
import com.hexagonal.animals.infrastructure.responsesExceptions.NotFoundException;
import com.hexagonal.animals.infrastructure.responsesExceptions.NotUpdatedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animales")
@Tag(name = "Animales", description = "API para la gestión de animales")
public class AnimalController {

    private final AnimalServicePort animalServicePort;

    public AnimalController(AnimalServicePort animalServicePort) {
        this.animalServicePort = animalServicePort;
    }

    @Operation(summary = "Crear un nuevo animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Animal creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "400", description = "Datos del animal inválidos")
    })
    @PostMapping
    public ResponseEntity<Animal> createAnimal(
            @Parameter(description = "Datos del animal a crear") @RequestBody Animal animal) {
        return new ResponseEntity<>(animalServicePort.saveAnimal(animal), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un animal por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal encontrado",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimalById(
            @Parameter(description = "ID del animal") @PathVariable Long id) {
        Optional<Animal> updatedAnimal = animalServicePort.getAnimalById(id);

        if (updatedAnimal.isPresent()) {
            return ResponseEntity.ok(animalServicePort.getAnimalById(id).get());
        } else {
            throw new NotFoundException();
        }
    }

    @Operation(summary = "Obtener todos los animales")
    @ApiResponse(responseCode = "200", description = "Lista de animales encontrada",
            content = @Content(schema = @Schema(implementation = Animal.class)))
    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalServicePort.getAllAnimals());
    }

    @Operation(summary = "Actualizar un animal existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Animal.class))),
            @ApiResponse(responseCode = "404", description = "Animal no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos del animal inválidos"),
            @ApiResponse(responseCode = "304", description = "Error al actualizar el animal")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(
            @Parameter(description = "ID del animal") @PathVariable Long id,
            @Parameter(description = "Nuevos datos del animal") @RequestBody Animal animal) {

        int result = animalServicePort.updateAnimal(animal, id);
        if (result > 0) {
            Optional<Animal> updatedAnimal = animalServicePort.getAnimalById(id);

            if (updatedAnimal.isPresent()) {
                return ResponseEntity.ok(updatedAnimal.get());
            } else {
                throw new NotFoundException();
            }

        } else if (result == 0) {
            throw new NotFoundException();
        } else if (result == -1) {
            throw new NotUpdatedException();
        }
        return ResponseEntity.internalServerError().build();
    }

    @Operation(summary = "Eliminar un animal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Animal eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Animal no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(
            @Parameter(description = "ID del animal") @PathVariable Long id) {
        if (animalServicePort.getAnimalById(id).isPresent()) {
            animalServicePort.deleteAnimal(id);
            return ResponseEntity.noContent().build();
        }
        throw new NotFoundException();
    }
}
