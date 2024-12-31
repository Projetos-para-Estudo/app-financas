package com.financas.app_financas.repository.user;


import com.financas.app_financas.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    List<User> findByUsernameIgnoreCase(String username);

    List<User> findByRoleName(String roleName);

    boolean existsByUsername(String username);

}
