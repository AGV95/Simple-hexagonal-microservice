package com.hexagonal.animals.infrastructure.repository;

import com.hexagonal.animals.infrastructure.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalJpaRepository extends JpaRepository<AnimalEntity, Long> {

}
