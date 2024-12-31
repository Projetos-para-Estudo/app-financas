package com.financas.app_financas.repository.cadastro;

import com.financas.app_financas.model.cadastro.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, UUID> {



    Optional<Cadastro> findByEmail(String email);

    Optional<Cadastro> findByUserId(UUID userId);

    Optional<Cadastro> findByTelefone(String telefone);


}
