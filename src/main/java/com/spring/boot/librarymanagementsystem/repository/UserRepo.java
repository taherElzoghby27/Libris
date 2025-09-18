package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserSystem, Long> {
    UserSystem findByUsername(String username);

    UserSystem findByEmail(String email);
}
