package com.hexagonal.animals.infrastructure.repository;

import com.hexagonal.animals.infrastructure.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AnimalJpaRepository extends JpaRepository<AnimalEntity, Long> {

    @Query("UPDATE AnimalEntity a SET a.name = :name, a.species = :species, a.age = :age WHERE a.id = :id")
    @Modifying
    @Transactional
    int update(@Param("id") Long id,
               @Param("name") String name,
               @Param("species") String species,
               @Param("age") int age);
}
