package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.PublisherService;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherResponseVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublishersResponseVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<PublisherDto>> addPublisher(@Valid @RequestBody PublisherDto publisherDto) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-publisher"))
                        .body(publisherService.createPublisher(publisherDto))
        );
    }

    @GetMapping("/{id}")
    public SuccessDto<ResponseEntity<PublisherDto>> getPublisher(@PathVariable Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.getPublisher(id))
        );
    }

    @GetMapping("/get-publisher-with-books/{id}")
    public SuccessDto<ResponseEntity<PublisherResponseVm>> getPublisherWithBooks(@PathVariable Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.getPublisherWithBooks(id))
        );
    }

    @GetMapping
    public SuccessDto<ResponseEntity<PublishersResponseVm>> getPublishers(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.getPublishers(page, size))
        );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<PublisherDto>> updatePublisher(@Valid @RequestBody PublisherUpdateVm publisherUpdateVm) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.updatePublisher(publisherUpdateVm))
        );
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public SuccessDto<ResponseEntity<String>> deletePublisher(@RequestParam Long id) {
        publisherService.deletePublisher(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Publisher deleted successfully")
        );
    }
}
