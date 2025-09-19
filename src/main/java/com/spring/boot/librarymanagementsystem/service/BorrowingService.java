package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.BorrowingDto;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingRequestVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingResponseVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingsResponseVm;

public interface BorrowingService {
    BorrowingDto addBorrowing(BorrowingRequestVm borrowingRequestVm);

    BorrowingResponseVm getBorrowingWithoutData(Long id);

    BorrowingDto getBorrowingWithData(Long id);

    BorrowingsResponseVm getAllBorrowings(int page, int size);

    BorrowingDto updateBorrowing(BorrowingUpdateVm borrowingUpdateVm);

    void deleteBorrowing(Long id);
}
