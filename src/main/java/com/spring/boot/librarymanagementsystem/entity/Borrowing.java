package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.BorrowingStatus;
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
    @Column(nullable = false)
    private LocalDateTime issuedAt;
    @Column(nullable = false)
    private LocalDateTime dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserSystem returnedByUser;
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus = BorrowingStatus.BORROWED;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Member member;
}
