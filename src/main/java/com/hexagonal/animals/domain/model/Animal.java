package com.hexagonal.animals.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Animal {

    private Long id;
    private String name;
    private String species;
    private int age;
    private String habitat;

    public Animal(Long id, String name, String species, int age, String habitat) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age = age;
        this.habitat = habitat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
