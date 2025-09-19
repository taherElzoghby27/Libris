package com.spring.boot.librarymanagementsystem.vm.activity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActivityRequestVm {
    @NotEmpty(message = "action must be not empty")
    private String action;
    @NotEmpty(message = "entity name must be not empty")
    private String entityName;
    private String details;
}
