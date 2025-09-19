package com.spring.boot.librarymanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookDto {
    private String title;
    private String summary;
    private String language;
    private Integer publicationYear;
    private String isbn;
    private String edition;
    private List<String> coverImages = new ArrayList<>();
    private PublisherDto publisher;
    private List<AuthorDto> authors = new ArrayList<>();
    private CategoryDto category;
}
