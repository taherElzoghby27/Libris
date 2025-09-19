package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.vm.MemberRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.MemberRequestVm;
import com.spring.boot.librarymanagementsystem.vm.MembersResponseVm;

public interface MemberService {
    MemberDto addMember(MemberRequestVm memberRequestVm);

    MembersResponseVm getMembers(int page, int size);

    MemberDto getMember(Long id);

    void deleteMember(Long id);

    MemberDto updateMember(MemberRequestUpdateVm memberRequestUpdateVm);
}
