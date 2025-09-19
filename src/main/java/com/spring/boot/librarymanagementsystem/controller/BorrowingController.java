package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.BorrowingDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.BorrowingService;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingRequestVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingResponseVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingsResponseVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;

    @PostMapping("/create-borrowing")
    public SuccessDto<ResponseEntity<BorrowingDto>> addBorrowing(@Valid @RequestBody BorrowingRequestVm vm) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-borrowing"))
                        .body(borrowingService.addBorrowing(vm))
        );
    }

    @PutMapping("/update-borrowing")
    public SuccessDto<ResponseEntity<BorrowingDto>> updateBorrowing(@Valid @RequestBody BorrowingUpdateVm vm) {
        return new SuccessDto<>(
                ResponseEntity.ok(borrowingService.updateBorrowing(vm))
        );
    }

    @GetMapping("/get-borrowing")
    public SuccessDto<ResponseEntity<BorrowingResponseVm>> getBorrowingWithoutData(@RequestParam Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(borrowingService.getBorrowingWithoutData(id))
        );
    }

    @GetMapping("/get-borrowing-with-data")
    public SuccessDto<ResponseEntity<BorrowingDto>> getBorrowingWithData(@RequestParam Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(borrowingService.getBorrowingWithData(id))
        );
    }

    @GetMapping("/get-borrowings")
    public SuccessDto<ResponseEntity<BorrowingsResponseVm>> getAllBorrowings(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(borrowingService.getAllBorrowings(page, size))
        );
    }


    @DeleteMapping("/delete-borrowing")
    public SuccessDto<ResponseEntity<String>> deleteBorrowing(@RequestParam Long id) {
        borrowingService.deleteBorrowing(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Borrowing deleted successfully")
        );
    }
}
