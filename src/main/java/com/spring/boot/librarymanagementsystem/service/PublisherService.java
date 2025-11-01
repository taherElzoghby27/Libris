package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherResponseVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublishersResponseVm;

public interface PublisherService {
    PublisherDto createPublisher(PublisherDto publisherDto);

    PublishersResponseVm getPublishers(int page, int size);

    void deletePublisher(Long id);

    PublisherDto updatePublisher(PublisherUpdateVm publisherUpdateVm);

    PublisherResponseVm getPublisherWithBooks(Long id);

    PublisherDto getPublisherWithId(Long id);
}
