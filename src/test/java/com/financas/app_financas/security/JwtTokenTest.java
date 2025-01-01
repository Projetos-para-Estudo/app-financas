package com.financas.app_financas.security;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtTokenTest {

    @Test
    public void deveGerarToken() {

        String token = JwtUtil.generateToken("Usuario_Teste");

        Assertions.assertNotNull(token);
        System.out.println("Token: " + token);

    }
}
