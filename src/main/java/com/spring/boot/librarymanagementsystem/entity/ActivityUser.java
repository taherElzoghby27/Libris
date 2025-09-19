package com.spring.boot.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log", schema = "LIBRARY_SYSTEM")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActivityUser extends BaseEntity<String> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserSystem user;

    @Column(length = 200, nullable = false)
    private String action;

    @Column(name = "entity_name", length = 100, nullable = false)
    private String entityName;
    @Column(columnDefinition = "jsonb")
    private String details;
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime timestamp = LocalDateTime.now();
}
