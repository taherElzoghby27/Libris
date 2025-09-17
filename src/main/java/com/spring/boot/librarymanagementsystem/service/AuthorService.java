package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAuthors();

    AuthorDto getAuthor(Long id);

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(AuthorDto authorDto);

    void deleteAuthor(Long id);
}
