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
    @Column(nullable = false, precision = 4)
    private Integer publicationYear;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String edition;
    @ElementCollection
    @CollectionTable(
            schema = "LIBRARY_SYSTEM",
            name = "BOOK_COVER_IMAGES",
            joinColumns = @JoinColumn(name = "book_id")
    )
    @Column(name = "image_url", nullable = false)
    private List<String> coverImages = new ArrayList<>();
    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;
    @ManyToMany
    @JoinTable(
            schema = "LIBRARY_SYSTEM",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"book_id", "author_id"})
    )
    private List<Author> authors = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @OneToMany(mappedBy = "book")
    private List<Borrowing> borrowings;
}
