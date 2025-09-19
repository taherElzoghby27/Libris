package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.vm.PublisherResponseVm;
import com.spring.boot.librarymanagementsystem.vm.PublishersResponseVm;

public interface PublisherService {
    PublisherDto createPublisher(PublisherDto publisherDto);

    PublishersResponseVm getPublishers(int page, int size);

    void deletePublisher(Long id);

    PublisherDto updatePublisher(PublisherDto publisherDto);

    PublisherResponseVm getPublisherWithBooks(Long id);

    PublisherDto getPublisher(Long id);
}
