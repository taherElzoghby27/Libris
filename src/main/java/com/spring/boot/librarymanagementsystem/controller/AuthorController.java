package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
