package com.financas.app_financas.controller;

import com.financas.app_financas.dto.LoginDTO;
import com.financas.app_financas.service.Auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * Endpoint de login para autenticar o usuário e gerar um token JWT.
     *
     * @param loginDTO Objeto contendo o username e password
     * @return Token JWT em caso de sucesso
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Chama o AuthService para autenticar e gerar o token
            String token = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            // Retorna erro 401 se as credenciais forem inválidas
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
