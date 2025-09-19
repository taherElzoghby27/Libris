package com.spring.boot.librarymanagementsystem.vm.borrowing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BorrowingsResponseVm {
    private List<BorrowingResponseVm> borrowings;
    private Long size;
}
