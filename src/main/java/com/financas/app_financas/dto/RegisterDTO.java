package com.financas.app_financas.dto;

import com.financas.app_financas.model.users.User;
import com.financas.app_financas.model.cadastro.Cadastro;
import com.financas.app_financas.model.role.Role;

public class RegisterDTO {
    private String username;
    private String password;
    private String roleName;
    private String nome;
    private String email;
    private String telefone;

    // Converte o DTO para um objeto User
    public User toUser(Role role) {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setRole(role);
        return user;
    }

    // Converte o DTO para um objeto Cadastro
    public Cadastro toCadastro(User user) {
        Cadastro cadastro = new Cadastro();
        cadastro.setNome(this.nome);
        cadastro.setEmail(this.email);
        cadastro.setTelefone(this.telefone);
        cadastro.setUser(user);
        return cadastro;
    }

    // Getters e Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
