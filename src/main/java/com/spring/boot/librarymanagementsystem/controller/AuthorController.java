package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.AuthorService;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorResponseVm;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorUpdateVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;


    @PostMapping("create-author")
    public SuccessDto<ResponseEntity<AuthorDto>> addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-author")).
                        body(authorService.createAuthor(authorDto))
        );
    }

    @GetMapping("get-author")
    public SuccessDto<ResponseEntity<AuthorDto>> getAuthor(@RequestParam Long authorId) {
        return new SuccessDto<>(
                ResponseEntity.ok(authorService.getAuthor(authorId))
        );
    }

    @GetMapping("get-authors")
    public SuccessDto<ResponseEntity<AuthorResponseVm>> getAuthors(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(authorService.getAuthors(page, size))
        );
    }

    @PutMapping("update-author")
    public SuccessDto<ResponseEntity<AuthorDto>> updateAuthor(@Valid @RequestBody AuthorUpdateVm authorUpdateVm) {
        return new SuccessDto<>(
                ResponseEntity.ok(authorService.updateAuthor(authorUpdateVm))
        );
    }

    @DeleteMapping("delete-author")
    public SuccessDto<ResponseEntity<String>> deleteAuthor(@RequestParam Long id) {
        authorService.deleteAuthor(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Author deleted successfully")
        );
    }

}
