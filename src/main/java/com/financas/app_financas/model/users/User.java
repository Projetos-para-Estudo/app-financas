package com.financas.app_financas.model.users;


import jakarta.persistence.*;
import com.financas.app_financas.model.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime date_creation;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime last_update;

    @ManyToOne
    @JoinColumn(name =  "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_role_id"))
    private Role role;


}
