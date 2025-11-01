package com.spring.boot.librarymanagementsystem.controller;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.dto.SuccessDto;
import com.spring.boot.librarymanagementsystem.service.MemberService;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestVm;
import com.spring.boot.librarymanagementsystem.vm.member.MembersResponseVm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public SuccessDto<ResponseEntity<MemberDto>> addMember(@Valid @RequestBody MemberRequestVm memberRequestVm) {
        MemberDto body = memberService.addMember(memberRequestVm);
        return new SuccessDto<>(ResponseEntity.created(URI.create("/members/" + body.getId())).body(body));
    }

    @GetMapping("/{id}")
    public SuccessDto<ResponseEntity<MemberDto>> getMember(@PathVariable Long id) {
        return new SuccessDto<>(ResponseEntity.ok(memberService.getMemberWithId(id)));
    }

    @GetMapping
    public SuccessDto<ResponseEntity<MembersResponseVm>> getMembers(@RequestParam int page, @RequestParam int size) {
        return new SuccessDto<>(ResponseEntity.ok(memberService.getMembers(page, size)));
    }

    @PatchMapping
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public SuccessDto<ResponseEntity<MemberDto>> updateMember(@Valid @RequestBody MemberRequestUpdateVm memberRequestUpdateVm) {
        return new SuccessDto<>(ResponseEntity.ok(memberService.updateMember(memberRequestUpdateVm)));
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public SuccessDto<ResponseEntity<String>> deleteMember(@RequestParam Long id) {
        memberService.deleteMember(id);
        return new SuccessDto<>(ResponseEntity.ok("Member deleted successfully"));
    }
}
