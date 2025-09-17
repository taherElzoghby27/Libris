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
        schema = "library",
        indexes = {
                @Index(name = "idx_publisher_name", columnList = "name")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Publisher extends BaseEntity<String> {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @OneToMany(mappedBy = "publisher")
    private List<Book> book = new ArrayList<>();
}
