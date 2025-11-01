package com.hexagonal.animals.infrastructure.repository;

import com.hexagonal.animals.infrastructure.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalJpaRepository extends JpaRepository<AnimalEntity, Long> {
}
