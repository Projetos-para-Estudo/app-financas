package com.financas.app_financas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {


    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 1000L * 60 * 30; // 30 minutos


    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY) // Usa a chave segura gerada
                .compact();
    }


    public static String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY) // Usa a mesma chave para validação
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
