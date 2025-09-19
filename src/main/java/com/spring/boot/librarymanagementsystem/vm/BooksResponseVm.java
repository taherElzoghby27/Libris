package com.spring.boot.librarymanagementsystem.vm;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
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
public class BooksResponseVm {
    private List<BookDto> books = new ArrayList<>();
    private Long size;
}
