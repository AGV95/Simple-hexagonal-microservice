package com.hexagonal.animals.domain.ports.output;

public interface RoleRepositoryPort {

    public boolean verifyUserPassword(String username, String password);
}
