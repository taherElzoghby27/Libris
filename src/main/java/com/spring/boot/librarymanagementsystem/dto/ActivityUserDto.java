package com.spring.boot.librarymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActivityUserDto {
    private Long id;
    @JsonProperty("user")
    private UserSystemDto user;
    private String action;
    @JsonProperty("entity")
    private String entityName;
    // stored as json string
    private String details;
    private LocalDateTime timestamp;
}
