package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.RoleDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.Role;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.repository.RoleRepo;
import com.spring.boot.librarymanagementsystem.repository.UserRepo;
import com.spring.boot.librarymanagementsystem.service.implementation.RoleServiceImpl;
import com.spring.boot.librarymanagementsystem.service.implementation.UserServiceImpl;
import com.spring.boot.librarymanagementsystem.utils.enums.RoleType;
import com.spring.boot.librarymanagementsystem.vm.user.UserSystemSignUpVm;
import com.spring.boot.librarymanagementsystem.vm.user.UserUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.user.UsersResponseVm;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RoleRepo roleRepo;
    @Mock
    private RoleServiceImpl roleService;
    @Mock
    private PasswordEncoder passwordEncoder; // fake bean
    private static UserSystem user1;
    private static UserSystem user2;

    @BeforeEach
    public void setUp() {
        user1 = UserSystem.builder()
                .email("tataamen678@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby27")
                .password("taherTAHER152002").build();
        user2 = UserSystem.builder()
                .email("tataamen12@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby272")
                .password("taherTAHER152002").build();
        user1.setId(1L);
    }

    @AfterAll
    public static void tearDown() {
        user1 = null;
        user2 = null;
    }

    @Test
    public void givenUsers_whenGetAllUsers_thenGetAllUsers() {
        //arrange
        List<UserSystem> users = List.of(user1, user2);
        Pageable pageable = PaginationService.getPageable(1, 10);
        Page<UserSystem> page = new PageImpl<>(users, pageable, users.size());
        when(userRepo.findAll(Mockito.any(Pageable.class))).thenReturn(page);
        //act
        UsersResponseVm usersResponseVmFounded = userService.getUsers(1, 10);
        //assert
        assertAll("Must equal", () -> assertEquals(users.size(), usersResponseVmFounded.getSize()), () -> assertEquals(users.get(0).getUsername(), usersResponseVmFounded.getUsers().get(0).getUsername()), () -> assertEquals(users.get(1).getUsername(), usersResponseVmFounded.getUsers().get(1).getUsername()));

        assertFalse(usersResponseVmFounded.getUsers().isEmpty(), "users list is not empty");
    }

    @Test
    public void givenNon_whenGetAllUsers_thenEmpty() {
        //arrange
        Pageable pageable = PaginationService.getPageable(1, 10);
        when(userRepo.findAll(Mockito.any(Pageable.class))).thenReturn(Page.empty(pageable));
        // Act + Assert
        assertThrows(NotFoundResourceException.class, () -> userService.getUsers(1, 10));
    }

    @Test
    public void givenUser_whenGetUserByEmail_thenGetIt() {
        //arrange
        when(userRepo.findByEmail(user1.getEmail())).thenReturn(user1);
        //act
        UserSystemDto userFounded = userService.getUserByEmail(user1.getEmail());
        //assert
        assertAll("must equal",
                () -> assertEquals(user1.getUsername(), userFounded.getUsername()),
                () -> assertEquals(user1.getFullName(), userFounded.getFullName()),
                () -> assertEquals(user1.getEmail(), userFounded.getEmail())
        );
    }

    @Test
    public void givenNoUser_whenGetUserByEmail_thenNotFound() {
        //arrange
        when(userRepo.findByEmail("taheramin@gmail.com"))
                .thenThrow(new NotFoundResourceException("user not found"));
        // Act + Assert
        assertThrows(
                NotFoundResourceException.class,
                () -> userService.getUserByEmail("taheramin@gmail.com")
        );
    }

    @Test
    public void givenUser_whenGetUserByUsername_thenGetIt() {
        //arrange
        when(userRepo.findByUsername(user1.getUsername())).thenReturn(user1);
        //act
        UserSystemDto userFounded = userService.getUserByUsername(user1.getUsername());
        //assert
        assertAll("must equal",
                () -> assertEquals(user1.getUsername(), userFounded.getUsername()),
                () -> assertEquals(user1.getFullName(), userFounded.getFullName()),
                () -> assertEquals(user1.getEmail(), userFounded.getEmail())
        );
    }

    @Test
    public void givenNonExistingUser_whenGetUserByUsername_thenNotFound() {
        //arrange
        when(userRepo.findByUsername("taheramin"))
                .thenThrow(new NotFoundResourceException("user not found"));
        // Act + Assert
        assertThrows(
                NotFoundResourceException.class,
                () -> userService.getUserByUsername("taheramin")
        );
    }

    @Test
    public void givenUser_whenGetUserById_thenGetIt() {
        //arrange1
        when(userRepo.findById(user1.getId())).thenReturn(Optional.of(user1));
        //act
        UserSystemDto userFounded = userService.getUserById(user1.getId());
        //assert
        assertAll("must equal",
                () -> assertEquals(user1.getUsername(), userFounded.getUsername()),
                () -> assertEquals(user1.getFullName(), userFounded.getFullName()),
                () -> assertEquals(user1.getEmail(), userFounded.getEmail())
        );
    }

    @Test
    public void givenNonExisting_whenGetUserById_thenNotFound() {
        //arrange
        when(userRepo.findById(1L))
                .thenThrow(new NotFoundResourceException("user not found"));
        // Act + Assert
        assertThrows(
                NotFoundResourceException.class,
                () -> userService.getUserById(1L)
        );
    }

    @Test
    public void givenUser_whenCreateUser_thenCreated() {
        //arrange
        UserSystemSignUpVm userSystemSignUpVm = UserSystemSignUpVm.builder()
                .email("tataamen678@gmail.com")
                .fullName("taher amin")
                .username("taherElzoghby27")
                .password("taherTAHER152002")
                .build();
        RoleDto roleDto = RoleDto.builder().role(RoleType.STAFF).build();
        when(roleService.getRoleByName(roleDto.getRole().toString())).thenReturn(roleDto);
        when(userRepo.save(Mockito.any(UserSystem.class)))
                .then(invocationOnMock -> invocationOnMock.getArgument(0));
        //act
        UserSystemDto userCreated = userService.createUserSystem(userSystemSignUpVm);
        //assert
        assertAll("must equal",
                () -> assertEquals(user1.getUsername(), userCreated.getUsername()),
                () -> assertEquals(user1.getFullName(), userCreated.getFullName()),
                () -> assertEquals(user1.getEmail(), userCreated.getEmail())
        );
    }

    @Test
    public void givenUser_whenUpdateUser_thenUpdated() {
        //arrange
        UserUpdateVm userUpdateVm = UserUpdateVm.builder()
                .id(1L)
                .email("tataamen678@gmail.com")
                .fullName("taher amin Elzoghby")
                .username("taherElzoghby27")
                .password("taherTAHER152002")
                .build();
        when(userRepo.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepo.save(Mockito.any(UserSystem.class)))
                .then(invocationOnMock -> invocationOnMock.getArgument(0));
        //act
        UserSystemDto userUpdated = userService.updateUserSystem(userUpdateVm);
        //assert
        assertAll("User fields updated",
                () -> assertEquals(user1.getUsername(), userUpdated.getUsername()),
                () -> assertNotEquals(user1.getFullName(), userUpdated.getFullName()),
                () -> assertEquals(user1.getEmail(), userUpdated.getEmail())
        );
    }

    @Test
    public void givenUser_whenDeleteIt_thenVerifyItCalled() {
        userService.deleteUserSystem(1L);
        verify(userRepo, times(1)).deleteById(1L);
    }

    @Test
    public void givenUser_whenDeleteIt_thenNoFoundIt() {
        //arrange
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
        //act
        userService.deleteUserSystem(1L);
        //assert
        assertThrows(NotFoundResourceException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void givenUser_whenUpdateUserRoles_thenUpdated() {
        //arrange
        Role roleStaff = Role.builder().role(RoleType.STAFF).build();
        RoleDto roleDtoAdmin = RoleDto.builder().role(RoleType.ADMIN).build();
        RoleDto roleDtoStaff = RoleDto.builder().role(RoleType.STAFF).build();
        user1.setRoles(List.of(roleStaff));
        when(userRepo.findById(1L)).thenReturn(Optional.of(user1));
        when(roleService.getRoleByName("ADMIN")).thenReturn(roleDtoAdmin);
        when(roleService.getRoleByName("STAFF")).thenReturn(roleDtoStaff);
        when(userRepo.save(Mockito.any(UserSystem.class)))
                .then(
                        invocationOnMock -> invocationOnMock.getArgument(0)
                );
        //act
        UserSystemDto userUpdated = userService.updateRolesForUserSystem(1L, List.of("ADMIN", "STAFF"));
        //assert
        assertAll("User fields updated",
                () -> assertNotEquals(user1.getRoles().size(), userUpdated.getRoles().size())
        );
    }
}

