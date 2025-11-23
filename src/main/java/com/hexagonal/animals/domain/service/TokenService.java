package com.hexagonal.animals.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    // Duración de los tokens en segundos, por ejemplo 2 horas
    private final long TOKEN_EXPIRATION_SEC = 7200;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String generateTokenForUser(String username) {
        String token = UUID.randomUUID().toString();


        // Guardamos en Redis con expiración
        // Podríamos guardar un objeto con roles, nombre, email, etc.
        redisTemplate.opsForValue().set(token, username, TOKEN_EXPIRATION_SEC, TimeUnit.SECONDS);
        return token;
    }

    public boolean isValidToken(String token) {
        String username = (String) redisTemplate.opsForValue().get(token);
        return username != null; // El token existe y no ha expirado
    }

    public String getUsernameFromToken(String token) {
        return (String) redisTemplate.opsForValue().get(token);
    }

    public void revokeToken(String token) {
        // Eliminar token de Redis manualmente si se desea revocarlo
        redisTemplate.delete(token);
    }

    public void revokeAllTokensForUser(String username) {
        Set<String> keys = redisTemplate.keys("*"); // Buscar todas las claves
        if (keys != null) {
            for (String key : keys) {
                String storedUsername = (String) redisTemplate.opsForValue().get(key);
                if (username.equals(storedUsername)) {
                    redisTemplate.delete(key); // Elimina los tokens del usuario
                }
            }
        }
    }
}