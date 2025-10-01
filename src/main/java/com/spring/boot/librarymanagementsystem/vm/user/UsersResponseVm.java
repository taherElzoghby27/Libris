package com.spring.boot.librarymanagementsystem.vm.user;

import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UsersResponseVm {
    private List<UserSystemDto> users;
    private Long size;
}
