package com.spring.boot.librarymanagementsystem.vm;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
public class BookRequestUpdateVm {
    private Long id;
    @NotEmpty(message = "title must be not empty")
    private String title;
    @NotEmpty(message = "summary must be not empty")
    private String summary;
    @Min(value = 1000, message = "publication year at least be 1000")
    @Max(value = 3000, message = "publication year at max be 3000")
    private Integer publicationYear;
    @NotEmpty(message = "isbn must be not empty")
    private String isbn;
    @NotEmpty(message = "edition must be not empty")
    private String edition;
    private List<String> coverImages = new ArrayList<>();
}
