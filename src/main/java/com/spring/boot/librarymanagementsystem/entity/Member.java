package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.MemberShipStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        schema = "LIBRARY_SYSTEM",
        indexes = {
                @Index(name = "idx_member_email", columnList = "email"),
                @Index(name = "idx_member_phone", columnList = "phone")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Member extends BaseEntity<String> {
    @Column(nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private LocalDateTime memberShipStartDate = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    private MemberShipStatus memberShipStatus;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrowing> borrowingList = new ArrayList<>();
}
