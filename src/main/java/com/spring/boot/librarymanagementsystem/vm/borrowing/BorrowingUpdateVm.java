package com.spring.boot.librarymanagementsystem.vm.borrowing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.utils.BorrowingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class BorrowingUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    @JsonProperty("book_id")
    private Long bookId;
    @JsonProperty("issued_by_user_id")
    private Long issuedByUserId;
    @JsonProperty("issued_at")
    private LocalDateTime issuedAt;
    @JsonProperty("due_date")
    private LocalDateTime dueDate;
    @JsonProperty("returned_by_user_id")
    private Long returnedByUser;
    private LocalDateTime returnedAt;
    @Enumerated(EnumType.STRING)
    private BorrowingStatus borrowingStatus;
    @JsonProperty("member_id")
    private Long member;
}
