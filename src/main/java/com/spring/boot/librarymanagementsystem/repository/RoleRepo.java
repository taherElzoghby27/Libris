package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query(value = "select r from Role r where r.role=:role")
    Role findByRole(String role);
}
