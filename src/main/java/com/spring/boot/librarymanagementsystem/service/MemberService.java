package com.spring.boot.librarymanagementsystem.service;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestVm;
import com.spring.boot.librarymanagementsystem.vm.member.MembersResponseVm;

public interface MemberService {
    MemberDto addMember(MemberRequestVm memberRequestVm);

    MembersResponseVm getMembers(int page, int size);

    MemberDto getMemberWithId(Long id);

    void deleteMember(Long id);

    MemberDto updateMember(MemberRequestUpdateVm memberRequestUpdateVm);
}
