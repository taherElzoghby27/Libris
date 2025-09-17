package com.spring.boot.librarymanagementsystem.entity;

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
public class Category extends BaseEntity<String> {
    private String name;
}
