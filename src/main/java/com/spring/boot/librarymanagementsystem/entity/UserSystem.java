package com.spring.boot.librarymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "library")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserSystem extends BaseEntity<String> {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false, length = 20)
    private String password;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private Long enabled = 1L;
}
