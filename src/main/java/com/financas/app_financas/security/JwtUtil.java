package com.financas.app_financas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.stereotype.Component;

@Component // Torna a classe um bean gerenciável pelo Spring
public class JwtUtil {

    private static final String SECRET = "dXNlX3VtYV9jaGF2ZV9zZWdyZXRhX3BhcmFfdG9rZW5z";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    private static final long EXPIRATION_TIME = 1000L * 60 * 30; // 30 minutos

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
