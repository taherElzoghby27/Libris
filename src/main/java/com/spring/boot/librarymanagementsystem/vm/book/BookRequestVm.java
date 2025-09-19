package com.spring.boot.librarymanagementsystem.vm.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class BookRequestVm {
    private Long id;
    @NotEmpty(message = "title must be not empty")
    private String title;
    @NotEmpty(message = "summary must be not empty")
    private String summary;
    @NotEmpty(message = "language must be not empty")
    private String language;
    @Min(value = 1000, message = "publication year at least be 1000")
    @Max(value = 3000, message = "publication year at max be 3000")
    private Integer publicationYear;
    @NotEmpty(message = "isbn must be not empty")
    private String isbn;
    @NotEmpty(message = "edition must be not empty")
    private String edition;
    private List<String> coverImages = new ArrayList<>();
    @NotNull(message = "publisher id must be not empty")
    @JsonProperty("publisher_id")
    private Long publisherId;
    @NotNull(message = "author id must be not empty")
    @JsonProperty("authors_id")
    private List<Long> authorsId;
    @NotNull(message = "category id must be not empty")
    @JsonProperty("category_id")
    private Long categoryId;
}
