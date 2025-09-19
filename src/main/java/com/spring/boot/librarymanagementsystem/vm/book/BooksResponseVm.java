package com.spring.boot.librarymanagementsystem.vm.book;
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
    private List<BookResponseVm> books = new ArrayList<>();
    private Long size;
}
