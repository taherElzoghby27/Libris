package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.entity.Publisher;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.PublisherMapper;
import com.spring.boot.librarymanagementsystem.repository.PublisherRepo;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.service.PublisherService;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherResponseVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublishersResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepo publisherRepo;

    @Override
    public PublisherDto createPublisher(PublisherDto publisherDto) {
        if (Objects.nonNull(publisherDto.getId())) {
            throw new BadRequestException("id must be null");
        }
        Publisher publisher = PublisherMapper.INSTANCE.toPublisher(publisherDto);
        publisher = publisherRepo.save(publisher);
        return PublisherMapper.INSTANCE.toPublisherDto(publisher);
    }

    @Override
    public PublishersResponseVm getPublishers(int page, int size) {
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<Publisher> result = publisherRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("publishers not found");
        }
        return new PublishersResponseVm(
                result.map(PublisherMapper.INSTANCE::toPublisherDto).getContent(),
                result.getTotalElements()
        );
    }

    @Transactional
    @Override
    public void deletePublisher(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        getPublisher(id);
        publisherRepo.deleteById(id);
    }

    @Override
    public PublisherDto updatePublisher(PublisherUpdateVm publisherUpdateVm) {
        boolean update = false;
        PublisherDto oldPublisherDto = getPublisher(publisherUpdateVm.getId());
        update = updateData(publisherUpdateVm, oldPublisherDto, update);
        if (update) {
            Publisher publisher = PublisherMapper.INSTANCE.toPublisher(oldPublisherDto);
            publisher = publisherRepo.save(publisher);
            return PublisherMapper.INSTANCE.toPublisherDto(publisher);
        }
        throw new BadRequestException("data must be different");
    }

    private static boolean updateData(PublisherUpdateVm publisherUpdateVm, PublisherDto oldPublisherDto, boolean update) {
        if (publisherUpdateVm.getName() != null && !Objects.equals(oldPublisherDto.getName(), publisherUpdateVm.getName())) {
            update = true;
            oldPublisherDto.setName(publisherUpdateVm.getName());
        }
        if (publisherUpdateVm.getAddress() != null && !Objects.equals(oldPublisherDto.getAddress(), publisherUpdateVm.getAddress())) {
            update = true;
            oldPublisherDto.setAddress(publisherUpdateVm.getAddress());
        }
        return update;
    }

    @Override
    public PublisherResponseVm getPublisherWithBooks(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Publisher> result = publisherRepo.findByIdWithBooks(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("publisher not found");
        }
        return PublisherMapper.INSTANCE.toPublisherResponseVm(result.get());
    }

    @Override
    public PublisherDto getPublisher(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Publisher> result = publisherRepo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("publisher not found");
        }
        return PublisherMapper.INSTANCE.toPublisherDto(result.get());
    }
}
