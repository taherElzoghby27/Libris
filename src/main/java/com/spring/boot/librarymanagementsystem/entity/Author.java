package com.spring.boot.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        schema = "LIBRARY_SYSTEM",
        indexes = {
                @Index(name = "idx_author_first_name", columnList = "firstName")
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"first_name", "last_name"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Author extends BaseEntity<String> {
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "bio", nullable = false)
    private String bio;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
