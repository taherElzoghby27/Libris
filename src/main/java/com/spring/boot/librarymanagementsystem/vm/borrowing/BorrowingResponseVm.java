package com.spring.boot.librarymanagementsystem.vm.borrowing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.BorrowingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BorrowingResponseVm {
    private LocalDateTime issuedAt;
    private LocalDateTime dueDate;
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus;
    @JsonProperty("member_id")
    private Long memberId;
}
