package com.financas.app_financas.service;

import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.user.UserRepository;
import com.financas.app_financas.security.JwtUtil;
import com.financas.app_financas.service.Auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;



    @Test
    public void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        // Cenário
        Mockito.when(userRepository.findByUsername("usuario_inexistente")).thenReturn(Optional.empty());

        // Ação e Verificação
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            authService.login("usuario_inexistente", "senha123");
        });
    }

    @Test
    public void deveLancarExcecaoQuandoSenhaInvalida() {
        // Cenário
        User user = new User();
        user.setUsername("usuario_teste");
        user.setPassword("senhaCriptografada");

        Mockito.when(userRepository.findByUsername("usuario_teste")).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches("senhaErrada", "senhaCriptografada")).thenReturn(false);

        // Ação e Verificação
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            authService.login("usuario_teste", "senhaErrada");
        });
    }
}
