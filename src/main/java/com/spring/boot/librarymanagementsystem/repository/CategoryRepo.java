package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    // Get categories WITHOUT fetching books (lazy default)
    @Query("SELECT c FROM Category c")
    List<Category> findAllCategoriesWithoutBooks();

    // Get categories WITH books (join fetch forces eager load)
    @Query("SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.books")
    List<Category> findAllCategoriesWithBooks();

    // Get category WITH books (join fetch forces eager load)
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.books on c.id=:id")
    Category findCategoryWithBooks(@Param("id") Long id);
}
