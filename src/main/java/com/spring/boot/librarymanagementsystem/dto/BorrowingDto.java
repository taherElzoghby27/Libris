package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long id;
    private BookDto book;
    @JsonProperty("issued_by_user")
    private UserSystemDto issuedByUser;
    @JsonProperty("issued_at")
    private LocalDateTime issuedAt;
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    @JsonProperty("returned_by_user")
    private UserSystemDto returnedByUser;
    @JsonProperty("returned_at")
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    @JsonProperty("borrowing_status")
    private BorrowingStatus borrowingStatus;
    private MemberDto member;
}
