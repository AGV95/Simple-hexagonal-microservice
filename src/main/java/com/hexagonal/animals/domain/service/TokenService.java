package com.hexagonal.animals.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    // Duración de los tokens en segundos, por ejemplo 2 horas
    private final long TOKEN_EXPIRATION_SEC = 7200;

    private Map<String, String> tokenStore = new HashMap<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.redis.enabled}")
    private boolean redisEnabled;

    public String generateTokenForUser(String username) {
        String token = UUID.randomUUID().toString();


        // Guardamos en Redis con expiración
        // Podríamos guardar un objeto con roles, nombre, email, etc.
        if (redisEnabled) {
            redisTemplate.opsForValue().set(token, username, TOKEN_EXPIRATION_SEC, TimeUnit.SECONDS);
        } else {
            tokenStore.put(token, username);
        }
        return token;
    }

    public boolean isValidToken(String token) {
        if (redisEnabled) {
            String username = (String) redisTemplate.opsForValue().get(token);
            return username != null; // El token existe y no ha expirado
        }
        return token != null && !token.isEmpty() && tokenStore.containsKey(token);
    }

    public String getUsernameFromToken(String token) {
        if (redisEnabled) {
            return (String) redisTemplate.opsForValue().get(token);

        } else {
            return tokenStore.get(token);
        }
    }

    public void revokeToken(String token) {

        if (redisEnabled) {
            // Eliminar token de Redis manualmente si se desea revocarlo
            redisTemplate.delete(token);

        } else {
            tokenStore.remove(token);
        }
    }

    public void revokeAllTokensForUser(String username) {
        if (redisEnabled) {
            Set<String> keys = redisTemplate.keys("*"); // Buscar todas las claves
            if (keys != null) {
                for (String key : keys) {
                    String storedUsername = (String) redisTemplate.opsForValue().get(key);
                    if (username.equals(storedUsername)) {
                        redisTemplate.delete(key); // Elimina los tokens del usuario
                    }
                }
            }
        } else {
            tokenStore.entrySet().removeIf(entry -> entry.getValue().equals(username));
        }
    }
}