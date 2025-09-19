package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    @Query("select distinct m from Member m left join fetch m.borrowingList b left join fetch b.books where m.id = :id")
    Optional<Member> findByIdWithBorrowings(@Param("id") Long id);
}
