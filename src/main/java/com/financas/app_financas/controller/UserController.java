package com.financas.app_financas.controller;

import com.financas.app_financas.dto.RegisterDTO;
import com.financas.app_financas.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        try {
            userService.cadastrarUsuario(registerDTO);
            return ResponseEntity.ok("Usuário e cadastro criados com sucesso!");
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
