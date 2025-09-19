package com.spring.boot.librarymanagementsystem.dto;

import com.spring.boot.librarymanagementsystem.utils.MemberShipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberDto {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime memberShipStartDate;
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    private MemberShipStatus memberShipStatus = MemberShipStatus.ACTIVE;
}
