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
                @Index(name = "idx_book_title", columnList = "title")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book extends BaseEntity<String> {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String summary;
    @Column(nullable = false)
    private String language;
    @Column(nullable = false, length = 4)
    private Long publicationYear;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String edition;
    @Column(nullable = false)
    @OneToMany(mappedBy = "book")
    private List<BookImage> coverImages = new ArrayList<>();
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;
    @ManyToMany
    @JoinTable(
            schema = "library",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "author_id"})
    )
    private List<Author> authors = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "books")
    private List<Borrowing> borrowings;
}
