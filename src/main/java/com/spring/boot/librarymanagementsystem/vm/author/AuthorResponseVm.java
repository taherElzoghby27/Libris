package com.spring.boot.librarymanagementsystem.vm.author;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorResponseVm {
    private List<AuthorDto> authors;
    private Long size;
}
