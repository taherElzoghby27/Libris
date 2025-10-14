package com.spring.boot.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log_user", schema = "LIBRARY_SYSTEM")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActivityUser extends BaseEntity<String> {
    @Column(name = "username")
    private String username;
    @Column(name = "action",length = 200, nullable = false)
    private String action;
    @Column(name = "entity_name", length = 100, nullable = false)
    private String entityName;
    @Column(name = "details")
    private String details;
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime timestamp = LocalDateTime.now();
}
