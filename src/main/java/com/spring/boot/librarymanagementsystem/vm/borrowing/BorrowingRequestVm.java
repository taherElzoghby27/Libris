package com.spring.boot.librarymanagementsystem.vm.borrowing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.enums.BorrowingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BorrowingRequestVm {
    @NotNull(message = "book id must be not null")
    @JsonProperty("book_id")
    private Long bookId;
    @NotNull(message = "issued by user id must be not null")
    @JsonProperty("issued_by_user_id")
    private Long issuedByUserId;
    @NotNull(message = "issued at must be not null")
    @JsonProperty("issued_at")
    private LocalDateTime issuedAt;
    @NotNull(message = "due date must be not null")
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    @JsonProperty("returned_by_user_id")
    private Long returnedByUser;
    @JsonProperty("returned_id")
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    @JsonProperty("borrowing_status")
    private BorrowingStatus borrowingStatus;
    @NotNull(message = "member id must be not null")
    @JsonProperty("member_id")
    private Long member;
}
