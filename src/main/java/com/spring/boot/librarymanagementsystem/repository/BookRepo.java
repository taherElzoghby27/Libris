package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    @Query("select distinct b from Book b " +
           "left join fetch b.authors " +
           "left join fetch b.publisher " +
           "left join fetch b.category " +
           "left join fetch b.coverImages " +
           "where b.id = :id")
    Optional<Book> findByIdWithData(@Param("id") Long id);

    @Query("select b from Book b ")
    Optional<Book> findBook(@Param("id") Long id);
}
