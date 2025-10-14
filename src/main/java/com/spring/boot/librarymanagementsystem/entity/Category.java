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
                @Index(name = "idx_category_name", columnList = "name")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Category extends BaseEntity<String> {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_category_parent"))
    @ManyToOne
    private Category parent;
    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();
    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();
}
