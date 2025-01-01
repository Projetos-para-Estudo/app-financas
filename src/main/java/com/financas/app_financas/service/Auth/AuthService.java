package com.financas.app_financas.service.Auth;


import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.user.UserRepository;
import com.financas.app_financas.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * Realiza o login do usuário e retorna o token JWT se as credenciais forem válidas.
     *
     * @param username O nome de usuário fornecido
     * @param password A senha fornecida
     * @return Um token JWT se as credenciais forem válidas
     */
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Logs para debugging
        System.out.println("Senha fornecida: " + password);
        System.out.println("Senha armazenada: " + user.getPassword());
        System.out.println("Senha válida: " + passwordEncoder.matches(password, user.getPassword()));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        // Gera o token JWT
        return JwtUtil.generateToken(username);
    }
}
