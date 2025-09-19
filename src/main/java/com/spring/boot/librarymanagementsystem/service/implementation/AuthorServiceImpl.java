package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.entity.Author;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.AuthorMapper;
import com.spring.boot.librarymanagementsystem.repository.AuthorRepo;
import com.spring.boot.librarymanagementsystem.service.AuthorService;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorResponseVm;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorUpdateVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<Author> result = authorRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("authors not found");
        }
        return new AuthorResponseVm(
                result.map(AuthorMapper.INSTANCE::toAuthorDto).getContent(),
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
    public AuthorDto updateAuthor(AuthorUpdateVm authorUpdateVm) {
        boolean update = false;
        AuthorDto oldAuthorDto = getAuthor(authorUpdateVm.getId());
        update = updateData(authorUpdateVm, oldAuthorDto, update);
        if (update) {
            Author author = AuthorMapper.INSTANCE.toAuthor(oldAuthorDto);
            author = authorRepo.save(author);
            return AuthorMapper.INSTANCE.toAuthorDto(author);
        }
        throw new BadRequestException("data must be different");
    }

    private static boolean updateData(AuthorUpdateVm authorUpdateVm, AuthorDto oldAuthorDto, boolean update) {
        if (authorUpdateVm.getFirstName() != null && !oldAuthorDto.getFirstName().equals(authorUpdateVm.getFirstName())) {
            update = true;
            oldAuthorDto.setFirstName(authorUpdateVm.getFirstName());
        }
        if (authorUpdateVm.getLastName() != null && !oldAuthorDto.getLastName().equals(authorUpdateVm.getLastName())) {
            update = true;
            authorUpdateVm.setLastName(authorUpdateVm.getLastName());
        }
        if (authorUpdateVm.getBio() != null && !oldAuthorDto.getBio().equals(authorUpdateVm.getBio())) {
            update = true;
            oldAuthorDto.setBio(authorUpdateVm.getBio());
        }
        return update;
    }

    @Override
    public void deleteAuthor(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        getAuthor(id);
        authorRepo.deleteById(id);
    }
}
