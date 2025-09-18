package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationService {
    private PaginationService() {
    }

    public static Pageable getPageable(int page, int size) {
        if (page < 1) {
            throw new BadRequestException("page must be greater than 0");
        }
        return PageRequest.of(page - 1, size);
    }
}
