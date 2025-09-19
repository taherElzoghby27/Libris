package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRepo extends JpaRepository<Borrowing, Long> {

    @Query("select b from Borrowing b where b.id = :id")
    Optional<Borrowing> findBorrowing(@Param("id") Long id);
}
