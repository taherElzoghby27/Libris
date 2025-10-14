package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.enums.BorrowingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        schema = "LIBRARY_SYSTEM",
        indexes = {
                @Index(name = "idx_borrow_borrowingStatus", columnList = "borrowing_status")
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Borrowing extends BaseEntity<String> {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Book book;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private UserSystem issuedByUser;
    @Column(name = "issued_at",nullable = false)
    private LocalDateTime issuedAt;
    @Column(name = "due_date",nullable = false)
    private LocalDateTime dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserSystem returnedByUser;
    @Column(name = "returned_at")
    private LocalDateTime returnedAt;
    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;
}
