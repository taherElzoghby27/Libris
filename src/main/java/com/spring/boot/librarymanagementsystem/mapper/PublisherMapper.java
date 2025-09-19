package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.entity.Publisher;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toPublisher(PublisherDto publisherDto);

    PublisherDto toPublisherDto(Publisher publisher);

    PublisherResponseVm toPublisherResponseVm(Publisher publisher);
}
