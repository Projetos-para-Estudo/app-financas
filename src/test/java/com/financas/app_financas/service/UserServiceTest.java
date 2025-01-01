package com.financas.app_financas.service;

import com.financas.app_financas.dto.RegisterDTO;
import com.financas.app_financas.model.cadastro.Cadastro;
import com.financas.app_financas.model.role.Role;
import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.cadastro.CadastroRepository;
import com.financas.app_financas.repository.role.RoleRepository;
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
    private RoleRepository roleRepository;

    @Mock
    private CadastroRepository cadastroRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void deveCadastrarUsuarioQuandoUsernameNaoExiste() {
        // Cenário
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("novoUsuario");
        registerDTO.setPassword("senha123");
        registerDTO.setRoleName("USER");
        registerDTO.setNome("Nome Completo");
        registerDTO.setEmail("email@teste.com");
        registerDTO.setTelefone("123456789");

        Role role = new Role(UUID.randomUUID(), "USER");

        Mockito.when(userRepository.existsByUsername("novoUsuario")).thenReturn(false);
        Mockito.when(roleRepository.findByName("USER")).thenReturn(Optional.of(role));
        Mockito.when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografada");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Mockito.when(cadastroRepository.save(Mockito.any(Cadastro.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ação
        User resultado = userService.cadastrarUsuario(registerDTO);

        // Verificação
        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("novoUsuario", resultado.getUsername());
        Assertions.assertEquals("senhaCriptografada", resultado.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
        Mockito.verify(cadastroRepository, Mockito.times(1)).save(Mockito.any(Cadastro.class));
    }
}
