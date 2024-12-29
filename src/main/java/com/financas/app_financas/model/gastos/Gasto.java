package com.financas.app_financas.model.gastos;


import com.financas.app_financas.model.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "gasto")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gasto {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        if (this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
