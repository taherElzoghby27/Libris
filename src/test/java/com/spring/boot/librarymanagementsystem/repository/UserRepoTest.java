package com.spring.boot.librarymanagementsystem.repository;

import com.spring.boot.librarymanagementsystem.config.TestConfig;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
public class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    private UserSystem user;

    @BeforeEach
    public void setUp() {
        user = UserSystem.builder()
                .username("admin")
                .password("admin")
                .email("admin@admin.com")
                .fullName("admin admin")
                .build();
    }

    @Test
    public void givenUser_whenFindByUserName_thenGetIt() {
        //arrange
        userRepo.save(user);
        //act
        UserSystem foundUser = userRepo.findByUsername(user.getUsername());
        //assert
        assertNotNull(foundUser, "user must be not null");

        assertAll("User fields",
                () -> assertEquals(user.getUsername(), foundUser.getUsername()),
                () -> assertEquals(user.getEmail(), foundUser.getEmail()),
                () -> assertEquals(user.getFullName(), foundUser.getFullName())
        );
    }

    @Test
    public void givenUser_whenFindByUserName_thenNotFound() {
        //arrange
        userRepo.save(user);
        //act
        UserSystem foundUser = userRepo.findByUsername("admin1");
        //assert
        assertNull(foundUser, "user must be null");
    }

    @Test
    public void givenUser_whenFindByEmail_thenGetIt() {
        //arrange
        userRepo.save(user);
        //act
        UserSystem foundUser = userRepo.findByEmail(user.getEmail());
        //assert
        assertNotNull(foundUser, "user must be not null");
        assertAll("User fields",
                () -> assertEquals(user.getUsername(), foundUser.getUsername()),
                () -> assertEquals(user.getEmail(), foundUser.getEmail()),
                () -> assertEquals(user.getFullName(), foundUser.getFullName())
        );
    }

    @Test
    public void givenUser_whenFindByEmail_thenNotFound() {
        //arrange
        userRepo.save(user);
        //act
        UserSystem foundUser = userRepo.findByEmail("admin1");
        //assert
        assertNull(foundUser, "user must be null");
    }
}
