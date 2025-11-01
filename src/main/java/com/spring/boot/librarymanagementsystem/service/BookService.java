package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestVm;
import com.spring.boot.librarymanagementsystem.vm.book.BooksResponseVm;

public interface BookService {
    BookDto addBook(BookRequestVm bookRequestVm);

    BooksResponseVm getAllBooksWithoutData(int page, int size);

    BookDto getBookById(Long id);

    void deleteBook(Long id);

    BookDto updateBook(BookRequestUpdateVm bookRequestUpdateVm);
}
