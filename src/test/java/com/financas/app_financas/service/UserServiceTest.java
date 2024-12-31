package com.financas.app_financas.service;

import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.user.UserRepository;
import com.financas.app_financas.service.User.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void deveCadastrarUsuarioQuandoUsernameNaoExiste() {
        // Cenário
        User user = new User();
        user.setUsername("novoUsuario");
        user.setPassword("senha123");

        Mockito.when(userRepository.existsByUsername("novoUsuario")).thenReturn(false);
        Mockito.when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User resultado = userService.cadastrarUsuario(user);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("novoUsuario", resultado.getUsername());
        Assertions.assertEquals("senhaCriptografada", resultado.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }
}
