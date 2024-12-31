package com.financas.app_financas.repository.renda;

import com.financas.app_financas.model.renda.EnumsRenda;
import com.financas.app_financas.model.renda.Renda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface RendaRepository extends JpaRepository<Renda, UUID> {


    List<Renda> findByUserId(UUID userId);

    List<Renda> findByUser_IdAndTipo(UUID userId, EnumsRenda.TipoRenda tipo);

    List<Renda> findByUser_IdAndStatusAndPeriodicidade(
            UUID userId,
            EnumsRenda.StatusRenda status,
            EnumsRenda.Periodicidade periodicidade
    );

    @Query("""
       SELECT COALESCE(SUM(r.valor), 0)
       FROM Renda r
       WHERE r.user.id = :userId
         AND r.status = 'ATIVA'
       """)
    BigDecimal somarValorRendasAtivas(@Param("userId") UUID userId);


    List<Renda> findByUser_IdAndStatus(UUID userId, EnumsRenda.StatusRenda status);

    Page<Renda> findByUser_Id(UUID userId, Pageable pageable);


}
