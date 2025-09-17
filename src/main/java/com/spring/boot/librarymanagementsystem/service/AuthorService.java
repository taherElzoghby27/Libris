package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.vm.AuthorResponseVm;

import java.util.List;

public interface AuthorService {
    AuthorResponseVm getAuthors(int page, int size);

    AuthorDto getAuthor(Long id);

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(AuthorDto authorDto);

    void deleteAuthor(Long id);
}
