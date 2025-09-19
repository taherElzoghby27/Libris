package com.spring.boot.librarymanagementsystem.dto;

import com.spring.boot.librarymanagementsystem.utils.enums.RoleType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
