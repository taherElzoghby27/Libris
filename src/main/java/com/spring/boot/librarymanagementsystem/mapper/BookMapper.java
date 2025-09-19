package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.entity.Book;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestVm;
import com.spring.boot.librarymanagementsystem.vm.book.BookResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "borrowings", ignore = true)
    Book toBook(BookRequestVm vm);

    BookDto toBookDto(Book book);

    BookResponseVm toBookResponseVm(Book book);


    Book toBook(BookDto bookDto);
}
