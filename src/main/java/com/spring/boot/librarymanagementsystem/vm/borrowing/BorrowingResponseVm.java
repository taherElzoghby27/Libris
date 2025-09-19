package com.spring.boot.librarymanagementsystem.vm.borrowing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.enums.BorrowingStatus;
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
    @JsonProperty("issued_at")
    private LocalDateTime issuedAt;
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    @JsonProperty("returned_at")
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    @JsonProperty("borrowing_status")
    private BorrowingStatus borrowingStatus;
    @JsonProperty("member_id")
    private Long memberId;
}
