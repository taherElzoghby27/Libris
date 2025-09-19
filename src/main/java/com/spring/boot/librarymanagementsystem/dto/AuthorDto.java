package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AuthorDto {
    private Long id;
    @JsonProperty("first_name")
    @NotEmpty(message = "first name must be not empty")
    private String firstName;
    @JsonProperty("last_name")
    @NotEmpty(message = "last name must be not empty")
    private String lastName;
    @NotEmpty(message = "bio must be not empty")
    private String bio;
}
