package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorResponseVm;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorUpdateVm;

public interface AuthorService {
    AuthorResponseVm getAuthors(int page, int size);

    AuthorDto getAuthor(Long id);

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(AuthorUpdateVm authorUpdateVm);

    void deleteAuthor(Long id);
}
