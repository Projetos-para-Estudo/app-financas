package com.financas.app_financas.model.role;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@Entity
public class Role {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(nullable = false, length = 50, unique = true)
    private String name;


}
