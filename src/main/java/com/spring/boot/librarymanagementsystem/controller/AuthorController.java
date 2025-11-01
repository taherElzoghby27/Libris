package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.AuthorService;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorResponseVm;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorUpdateVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<AuthorDto>> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        AuthorDto author = authorService.createAuthor(authorDto);
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("/authors/" + author.getId())).
                        body(author)
        );
    }

    @GetMapping("/{authorId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<AuthorDto>> getAuthor(@PathVariable Long authorId) {
        return new SuccessDto<>(
                ResponseEntity.ok(authorService.getAuthorById(authorId))
        );
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<AuthorResponseVm>> getAuthors(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(authorService.getAuthors(page, size))
        );
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<AuthorDto>> updateAuthor(@Valid @RequestBody AuthorUpdateVm authorUpdateVm) {
        return new SuccessDto<>(
                ResponseEntity.ok(authorService.updateAuthor(authorUpdateVm))
        );
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<String>> deleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Author deleted successfully")
        );
    }

}
