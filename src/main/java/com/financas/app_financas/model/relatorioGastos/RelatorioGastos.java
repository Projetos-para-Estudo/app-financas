package com.financas.app_financas.model.relatorioGastos;


import com.fasterxml.jackson.databind.JsonNode;
import com.financas.app_financas.jpa.JsonNodeConverter;
import com.financas.app_financas.model.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "RelatorioGasto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioGastos {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal total_gasto;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal resto_orcamento;

    @Column(nullable = false, columnDefinition = "json")
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode gastos;

    @Column(nullable = false, name = "criacao")
    private LocalDateTime criacao;

    @PrePersist
    protected void onCreate(){
        this.criacao = LocalDateTime.now();
    }

}
