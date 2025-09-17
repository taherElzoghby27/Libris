package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.repository.AuthorRepo;
import com.spring.boot.librarymanagementsystem.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;
    @Override
    public List<AuthorDto> getAuthors() {
        authorRepo.findAll();
    }

    @Override
    public AuthorDto getAuthor(Long id) {

    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        return null;
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto) {
        return null;
    }

    @Override
    public void deleteAuthor(Long id) {

    }
    private static Pageable getPageable(int page, int size) {
        if (page < 1) {
            throw new BadRequestException("error.min.one.page");
        }
        return PageRequest.of(page - 1, size);
    }
}
