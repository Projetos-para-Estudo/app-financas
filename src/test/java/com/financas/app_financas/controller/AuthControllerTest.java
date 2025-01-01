package com.financas.app_financas.controller;

import com.financas.app_financas.dto.LoginDTO;
import com.financas.app_financas.service.Auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

public class AuthControllerTest {

    @Test
    public void deveRetornarTokenQuandoCredenciaisSaoValidas() {
        // Cenário
        AuthService authService = Mockito.mock(AuthService.class);
        AuthController authController = new AuthController();
        authController.authService = authService; // Injeta o mock manualmente

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("usuario_teste");
        loginDTO.setPassword("senha123");

        // Simula o comportamento do AuthService
        Mockito.when(authService.login("usuario_teste", "senha123"))
                .thenReturn("token_jwt");

        // Ação
        ResponseEntity<String> response = authController.login(loginDTO);

        // Verificação
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("token_jwt", response.getBody());
    }

    @Test
    public void deveRetornarErroQuandoCredenciaisSaoInvalidas() {
        // Cenário
        AuthService authService = Mockito.mock(AuthService.class);
        AuthController authController = new AuthController();
        authController.authService = authService; // Injeta o mock manualmente

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("usuario_teste");
        loginDTO.setPassword("senhaErrada");

        // Simula o comportamento do AuthService
        Mockito.when(authService.login("usuario_teste", "senhaErrada"))
                .thenThrow(new IllegalArgumentException("Credenciais inválidas"));

        // Ação
        ResponseEntity<String> response = authController.login(loginDTO);

        // Verificação
        Assertions.assertEquals(401, response.getStatusCodeValue());
        Assertions.assertEquals("Credenciais inválidas", response.getBody());
    }
}
