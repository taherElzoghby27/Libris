package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.vm.BookRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.BookRequestVm;
import com.spring.boot.librarymanagementsystem.vm.BooksResponseVm;

public interface BookService {
    BookDto addBook(BookRequestVm bookRequestVm);

    BooksResponseVm getAllBooksWithoutData(int page, int size);

    BookDto getBookWithData(Long id);

    BookDto getBook(Long id);

    void deleteBook(Long id);

    BookDto updateBook(BookRequestUpdateVm bookRequestUpdateVm);
}
