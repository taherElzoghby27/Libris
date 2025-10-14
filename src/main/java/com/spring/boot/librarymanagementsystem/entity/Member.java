package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.enums.MemberShipStatus;
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
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "member_ship_start_date", nullable = false)
    private LocalDateTime memberShipStartDate = LocalDateTime.now();
    @Column(name = "member_ship_end_date", nullable = false)
    private LocalDateTime memberShipEndDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "member_ship_status")
    private MemberShipStatus memberShipStatus;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Borrowing> borrowingList = new ArrayList<>();
}
