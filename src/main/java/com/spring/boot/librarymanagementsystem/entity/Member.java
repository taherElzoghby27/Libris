package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.MemberShipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(schema = "library")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Member extends BaseEntity<String> {
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    private LocalDateTime memberShipStartDate;
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    private MemberShipStatus memberShipStatus = MemberShipStatus.ACTIVE;
}
