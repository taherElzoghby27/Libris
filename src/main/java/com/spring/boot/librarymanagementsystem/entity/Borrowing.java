package com.spring.boot.librarymanagementsystem.entity;

import com.spring.boot.librarymanagementsystem.utils.BorrowingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "library")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Borrowing extends BaseEntity<String> {
    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus = BorrowingStatus.BORROWED;
}
