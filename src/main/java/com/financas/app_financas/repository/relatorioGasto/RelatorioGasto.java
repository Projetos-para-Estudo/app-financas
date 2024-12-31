package com.financas.app_financas.repository.relatorioGasto;


import com.financas.app_financas.model.relatorioGastos.RelatorioGastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RelatorioGasto extends JpaRepository<RelatorioGastos, UUID> {


    List<RelatorioGasto> findByUserId(UUID userId);

    List<RelatorioGasto> findByUserIdAndCriacaoBetween(UUID userId, LocalDateTime start, LocalDateTime end);

    List<RelatorioGasto> findByCriacaoBefore(LocalDateTime dataLimite);

}
