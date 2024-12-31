package com.financas.app_financas.repository.parcela;


import com.financas.app_financas.model.parcela.Parcela;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, UUID> {

    /**
     * Busca todas as parcelas de um determinado gasto.
     */
    Page<Parcela> findByGastoUserId(UUID userId, Pageable pageable);

    /**
     * Busca todas as parcelas do usuário (através do gasto -> user).
     */
    List<Parcela> findByGastoUserId(UUID userId);

    /**
     * Busca todas as parcelas que estão pagas (paga = true) ou não pagas (paga = false).
     */
    List<Parcela> findByPaga(boolean paga);

    /**
     * Busca parcelas atrasadas de um usuário (data de vencimento anterior a 'dataLimite' e não pagas).
     */
    List<Parcela> findByGastoUserIdAndPagaFalseAndDataVencimentoBefore(UUID userId, LocalDate dataLimite);

    /**
     * Busca parcelas a vencer de um usuário, ou seja, não pagas e com vencimento
     * entre hoje e 'dataLimite' (exemplo de filtro).
     */
    List<Parcela> findByGastoUserIdAndPagaFalseAndDataVencimentoBetween(
            UUID userId,
            LocalDate start,
            LocalDate end
    );
}

