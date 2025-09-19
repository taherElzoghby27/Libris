package com.spring.boot.librarymanagementsystem.vm.publisher;

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
public class PublisherResponseVm {
    private String name;
    private String address;
    private List<BookDto> book = new ArrayList<>();
}
