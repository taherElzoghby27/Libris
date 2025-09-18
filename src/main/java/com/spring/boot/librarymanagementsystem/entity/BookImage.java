package com.spring.boot.librarymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "LIBRARY_SYSTEM")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookImage extends BaseEntity<String> {
    private String coverImage;
    @JoinColumn(nullable = false)
    @ManyToOne
    private Book book;
}
