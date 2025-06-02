package org.acme.service;


import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class AuthService {

    public String generateJwt() {
        long nowSecs = System.currentTimeMillis() / 1000;
        long expSecs = nowSecs + 3600;

        return Jwt.issuer("auth-service")
                .subject("auth-service")
                .groups("Admin")
                .expiresAt(expSecs)
                .sign();
    }
}
