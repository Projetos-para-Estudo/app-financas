package com.financas.app_financas.repository.role;


import com.financas.app_financas.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {


    Optional<Role> findByName(String name);

    boolean existsByName(String name);
}
