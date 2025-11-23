package com.hexagonal.animals.infrastructure.rest;

import com.hexagonal.animals.domain.service.RoleService;
import com.hexagonal.animals.domain.service.TokenService;
import com.hexagonal.animals.infrastructure.responsesExceptions.UserNotAuthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
@Tag(name = "Login", description = "API para autenticación de usuarios")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Login exitoso"
            ),
            @ApiResponse(responseCode = "401", description = "Usuario o contraseña inválidos")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validar credenciales: esto podría implicar consultar una base de datos

        if (roleService.verifyUserPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
            String token = tokenService.generateTokenForUser(loginRequest.getUsername());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            throw new UserNotAuthorizedException();
        }
    }
}

class LoginRequest {
    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    // Getters y Setters
}
