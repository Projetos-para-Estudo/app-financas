package com.financas.app_financas.service.User;

import com.financas.app_financas.model.users.User;
import com.financas.app_financas.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private com.financas.app_financas.repository.user.UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User cadastrarUsuario(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username já existe!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public Optional<User> buscarPorUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
