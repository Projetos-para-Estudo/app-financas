package com.financas.app_financas.model.parcela;

import com.financas.app_financas.model.gastos.Gasto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "parcela")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parcela {

    @Id
    @GeneratedValue
    private UUID id;


    @ManyToOne
    @JoinColumn(
            name = "id_gasto",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_gasto")
    )
    private Gasto gasto;


    @Column(name = "numero_parcela", nullable = false)
    private Integer numeroParcela;

    @Column(name = "valor_parcela", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorParcela;


    @Column(name = "taxa_juros", precision = 5, scale = 2)
    private BigDecimal taxaJuros;


    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;


    @Column(nullable = false)
    private Boolean paga = false;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
