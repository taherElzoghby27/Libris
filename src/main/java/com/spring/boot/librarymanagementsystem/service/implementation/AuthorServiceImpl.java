package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.entity.Author;
import com.spring.boot.librarymanagementsystem.exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.AuthorMapper;
import com.spring.boot.librarymanagementsystem.repository.AuthorRepo;
import com.spring.boot.librarymanagementsystem.service.AuthorService;
import com.spring.boot.librarymanagementsystem.vm.AuthorResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;

    @Override
    public AuthorResponseVm getAuthors(int page, int size) {
        Pageable pageable = getPageable(page, size);
        Page<Author> result = authorRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("authors not found");
        }
        return new AuthorResponseVm(
                result.getContent().stream().map(AuthorMapper.INSTANCE::toAuthorDto).toList(),
                result.getTotalElements()
        );
    }

    @Override
    public AuthorDto getAuthor(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Author> result = authorRepo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("author not found");
        }
        return AuthorMapper.INSTANCE.toAuthorDto(result.get());
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        if (Objects.nonNull(authorDto.getId())) {
            throw new BadRequestException("id must be null");
        }
        Author author = AuthorMapper.INSTANCE.toAuthor(authorDto);
        author = authorRepo.save(author);
        return AuthorMapper.INSTANCE.toAuthorDto(author);
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto) {
        if (Objects.isNull(authorDto.getId())) {
            throw new BadRequestException("id must be not null");
        }
        getAuthor(authorDto.getId());
        Author author = AuthorMapper.INSTANCE.toAuthor(authorDto);
        author = authorRepo.save(author);
        return AuthorMapper.INSTANCE.toAuthorDto(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        getAuthor(id);
        authorRepo.deleteById(id);
    }

    private static Pageable getPageable(int page, int size) {
        if (page < 1) {
            throw new BadRequestException("page must be greater than 0");
        }
        return PageRequest.of(page - 1, size);
    }
}
