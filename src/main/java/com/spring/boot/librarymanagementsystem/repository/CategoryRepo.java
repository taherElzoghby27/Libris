package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c")
    List<Category> findAllCategoriesWithoutBooks();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.books where c.id=:id")
    Category findCategoryWithBooks(@Param("id") Long id);
}
