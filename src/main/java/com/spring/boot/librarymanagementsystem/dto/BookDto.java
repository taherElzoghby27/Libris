package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long id;
    private String title;
    private String summary;
    private String language;
    @JsonProperty("publication_year")
    private Integer publicationYear;
    private String isbn;
    private String edition;
    @JsonProperty("cover_images")
    private List<String> coverImages = new ArrayList<>();
    private PublisherDto publisher;
    private List<AuthorDto> authors = new ArrayList<>();
    private CategoryDto category;
}
