package com.financas.app_financas.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @InjectMocks
    private JwtUtil jwtUtil;

    @Test
    public void deveGerarToken() {
        String token = jwtUtil.generateToken("Usuario_Teste");

        Assertions.assertNotNull(token);
        System.out.println("Token: " + token);
    }
}
