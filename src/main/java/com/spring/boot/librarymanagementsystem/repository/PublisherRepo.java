package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Long> {

    @Query("select distinct p from Publisher p left join fetch p.book where p.id = :id")
    Optional<Publisher> findByIdWithBooks(@Param("id") Long id);
}
