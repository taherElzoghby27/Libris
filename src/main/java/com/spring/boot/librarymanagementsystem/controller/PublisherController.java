package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.PublisherService;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublisherResponseVm;
import com.spring.boot.librarymanagementsystem.vm.publisher.PublishersResponseVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("create-publisher")
    public SuccessDto<ResponseEntity<PublisherDto>> addPublisher(@Valid @RequestBody PublisherDto publisherDto) {
        return new SuccessDto<>(
                ResponseEntity.created(URI.create("create-publisher"))
                        .body(publisherService.createPublisher(publisherDto))
        );
    }

    @GetMapping("get-publisher")
    public SuccessDto<ResponseEntity<PublisherDto>> getPublisher(@RequestParam Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.getPublisher(id))
        );
    }

    @GetMapping("get-publisher-with-books")
    public SuccessDto<ResponseEntity<PublisherResponseVm>> getPublisherWithBooks(@RequestParam Long id) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.getPublisherWithBooks(id))
        );
    }

    @GetMapping("get-publishers")
    public SuccessDto<ResponseEntity<PublishersResponseVm>> getPublishers(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.getPublishers(page, size))
        );
    }

    @PutMapping("update-publisher")
    public SuccessDto<ResponseEntity<PublisherDto>> updatePublisher(@Valid @RequestBody PublisherDto publisherDto) {
        return new SuccessDto<>(
                ResponseEntity.ok(publisherService.updatePublisher(publisherDto))
        );
    }

    @DeleteMapping("delete-publisher")
    public SuccessDto<ResponseEntity<String>> deletePublisher(@RequestParam Long id) {
        publisherService.deletePublisher(id);
        return new SuccessDto<>(
                ResponseEntity.ok("Publisher deleted successfully")
        );
    }
}
