package com.spring.boot.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublisherDto {
    private Long id;
    @NotEmpty(message = "name must be not empty")
    private String name;
    @NotEmpty(message = "address must be not empty")
    private String address;
}
