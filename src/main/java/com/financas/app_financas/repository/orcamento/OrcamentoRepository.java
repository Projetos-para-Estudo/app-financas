package com.financas.app_financas.repository.orcamento;


import com.financas.app_financas.model.orcamento.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, UUID> {



    List<Orcamento> findByUserId(UUID userId);


    List<Orcamento> findByUserIdAndStartDateBetween(UUID userId, LocalDate start, LocalDate end);


    List<Orcamento> findByUserIdAndCategory(UUID userId, String category);


    @Query("""
           SELECT o 
           FROM Orcamento o 
           WHERE o.user.id = :userId 
             AND o.startDate <= CURRENT_DATE 
             AND o.endDate >= CURRENT_DATE
           """)
    List<Orcamento> findOrcamentosAtuais(UUID userId);


    Optional<Orcamento> findTopByUserIdOrderByDateCreationDesc(UUID userId);
}
