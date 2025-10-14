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
                @Index(name = "idx_publisher_name", columnList = "name")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Publisher extends BaseEntity<String> {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "address", nullable = false)
    private String address;
    @OneToMany(mappedBy = "publisher")
    private List<Book> book = new ArrayList<>();
}
