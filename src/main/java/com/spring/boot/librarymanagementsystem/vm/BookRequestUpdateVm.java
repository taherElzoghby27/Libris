package com.spring.boot.librarymanagementsystem.vm;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class BookRequestUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    private String title;
    private String summary;
    @Min(value = 1000, message = "publication year at least be 1000")
    @Max(value = 3000, message = "publication year at max be 3000")
    private Integer publicationYear;
    private String isbn;
    private String edition;
    private List<String> coverImages = new ArrayList<>();
}
