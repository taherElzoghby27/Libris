package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.entity.Author;
import com.spring.boot.librarymanagementsystem.vm.author.AuthorUpdateVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toAuthorDto(Author author);

    Author toAuthor(AuthorDto authorDto);

    Author toAuthor(AuthorUpdateVm authorUpdateVm);
}
