package com.spring.boot.librarymanagementsystem.vm.publisher;

import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PublishersResponseVm {
    private List<PublisherDto> publishers;
    private Long size;
}
