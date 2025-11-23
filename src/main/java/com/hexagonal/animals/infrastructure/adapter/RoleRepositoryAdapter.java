package com.hexagonal.animals.infrastructure.adapter;

import com.hexagonal.animals.domain.ports.output.RoleRepositoryPort;
import com.hexagonal.animals.infrastructure.repository.RoleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleJpaRepository roleJpaRepository;

    public RoleRepositoryAdapter(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public boolean verifyUserPassword(String username, String password) {
        // Aquí iría la lógica para verificar el usuario y la contraseña,
        // por ejemplo, consultando una base de datos o un servicio externo.
        return roleJpaRepository.verifyUser(username, password) == 1;
    }
}
