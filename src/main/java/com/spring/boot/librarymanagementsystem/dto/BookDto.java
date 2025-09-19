package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.boot.librarymanagementsystem.entity.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Min(1000)
    @Max(9999)
    private Integer publicationYear;
    private String isbn;
    private String edition;
    private List<BookImage> coverImages = new ArrayList<>();
    private Publisher publisher;
    private List<Author> authors = new ArrayList<>();
    @JsonProperty("category_id")
    private Long categoryId;
    private List<Borrowing> borrowings;
}
