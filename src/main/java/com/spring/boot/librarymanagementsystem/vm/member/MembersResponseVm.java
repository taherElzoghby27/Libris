package com.spring.boot.librarymanagementsystem.vm.member;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MembersResponseVm {
    private List<MemberDto> members;
    private Long size;
}
