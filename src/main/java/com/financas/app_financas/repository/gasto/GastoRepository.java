package com.financas.app_financas.repository.gasto;

import com.financas.app_financas.model.gastos.FormaPagamento;
import com.financas.app_financas.model.gastos.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, UUID> {

    List<Gasto> findByUserIdAndCategoryIgnoreCase(UUID userId, String category);

    List<Gasto> findByUserIdAndFormaPagamento(UUID userId, FormaPagamento formaPagamento);

    List<Gasto> findByUserIdAndValorTotalGreaterThan(UUID userId, BigDecimal valorMin);
    List<Gasto> findByUserIdAndValorTotalLessThan(UUID userId, BigDecimal valorMax);

    List<Gasto> findByUserIdAndCreatedAtBetween(UUID userId, LocalDateTime start, LocalDateTime end);


    List<Gasto> findByUserIdAndParcelado(UUID userId, boolean parcelado);
}
