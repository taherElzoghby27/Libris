package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "LIBRARY_SYSTEM", name = "role_entity")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @ManyToMany(mappedBy = "roles")
    private List<UserSystem> users = new ArrayList<>();
}
