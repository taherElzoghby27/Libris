package com.spring.boot.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        //schema = "LIBRARY_SYSTEM",
        indexes = {
                @Index(name = "idx_user_username", columnList = "username"),
                @Index(name = "idx_user_email", columnList = "email")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserSystem extends BaseEntity<String> {
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "enabled")
    private Boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = "LIBRARY_SYSTEM",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"user_id", "role_id"}
            )
    )
    private List<Role> roles = new ArrayList<>();

}
