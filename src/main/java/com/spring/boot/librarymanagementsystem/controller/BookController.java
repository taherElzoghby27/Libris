package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.BookService;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestVm;
import com.spring.boot.librarymanagementsystem.vm.book.BooksResponseVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public SuccessDto<ResponseEntity<BookDto>> addBook(@Valid @RequestBody BookRequestVm bookRequestVm) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-book"))
                        .body(bookService.addBook(bookRequestVm))
        );
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public SuccessDto<ResponseEntity<BookDto>> updateBook(@Valid @RequestBody BookRequestUpdateVm bookRequestUpdateVm) {
        return new SuccessDto<>(
                ResponseEntity.ok(bookService.updateBook(bookRequestUpdateVm))
        );
    }

    @GetMapping("/{id}")
    public SuccessDto<ResponseEntity<BookDto>> getBook(@PathVariable Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(bookService.getBook(id))
        );
    }

    @GetMapping
    public SuccessDto<ResponseEntity<BooksResponseVm>> getBooks(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(bookService.getAllBooksWithoutData(page, size))
        );
    }


    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public SuccessDto<ResponseEntity<String>> deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Book deleted successfully")
        );
    }
}
