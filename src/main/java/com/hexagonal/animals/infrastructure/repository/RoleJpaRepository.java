package com.hexagonal.animals.infrastructure.repository;

import com.hexagonal.animals.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    @Query("select count(*) from RoleEntity rl where rl.username = :username and rl.password = :password")
    @Transactional
    int verifyUser(@Param("username") String user,
                   @Param("password") String password);
}
