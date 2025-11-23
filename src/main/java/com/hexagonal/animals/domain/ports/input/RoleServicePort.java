package com.hexagonal.animals.domain.ports.input;

public interface RoleServicePort {

    public boolean verifyUserPassword(String username, String password);
}
