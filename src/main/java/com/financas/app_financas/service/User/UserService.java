package com.financas.app_financas.service.User;

import com.financas.app_financas.model.cadastro.Cadastro;
import com.financas.app_financas.model.role.Role;
import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.cadastro.CadastroRepository;
import com.financas.app_financas.repository.role.RoleRepository;
import com.financas.app_financas.repository.user.UserRepository;
import com.financas.app_financas.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User cadastrarUsuario(RegisterDTO registerDTO) {
        // Verifica se o username já existe
        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O username já existe! Escolha outro.");
        }

        // Busca a role
        Role role = roleRepository.findByName(registerDTO.getRoleName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role não encontrada: " + registerDTO.getRoleName()));

        // Converte DTO para User
        User user = registerDTO.toUser(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Salva User
        userRepository.save(user);

        // Converte DTO para Cadastro
        Cadastro cadastro = registerDTO.toCadastro(user);

        // Salva Cadastro
        cadastroRepository.save(cadastro);

        return user;
    }
}