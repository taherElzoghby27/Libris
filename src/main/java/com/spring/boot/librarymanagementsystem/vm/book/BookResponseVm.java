package com.spring.boot.librarymanagementsystem.vm.book;

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
public class BookResponseVm {
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
}
