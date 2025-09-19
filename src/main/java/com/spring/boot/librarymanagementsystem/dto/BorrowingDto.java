package com.spring.boot.librarymanagementsystem.dto;

import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.utils.BorrowingStatus;
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
public class BorrowingDto {
    private BookDto book;
    private UserSystem issuedByUser;
    private LocalDateTime issuedAt;
    private LocalDateTime dueDate;
    private UserSystemDto returnedByUser;
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus;
    private MemberDto member;
}
