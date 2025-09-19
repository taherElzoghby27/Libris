package com.spring.boot.librarymanagementsystem.vm.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublisherUpdateVm {
    @NotNull(message = "id must be not null")
    private Long id;
    private String name;
    private String address;
}
