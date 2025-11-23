package com.hexagonal.animals.domain.service;

import com.hexagonal.animals.domain.ports.input.RoleServicePort;
import com.hexagonal.animals.domain.ports.output.RoleRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServicePort {

    private final RoleRepositoryPort roleRepositoryPort;

    public RoleService(RoleRepositoryPort roleRepositoryPort) {
        this.roleRepositoryPort = roleRepositoryPort;
    }

    @Override
    public boolean verifyUserPassword(String username, String password) {
        return roleRepositoryPort.verifyUserPassword(username, password);
    }
}
