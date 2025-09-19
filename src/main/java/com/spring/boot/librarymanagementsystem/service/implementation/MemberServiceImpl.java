package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.entity.Member;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.MemberMapper;
import com.spring.boot.librarymanagementsystem.repository.MemberRepo;
import com.spring.boot.librarymanagementsystem.service.MemberService;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.vm.MemberRequestVm;
import com.spring.boot.librarymanagementsystem.vm.MembersResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;

    @Override
    public MemberDto addMember(MemberRequestVm memberRequestVm) {
        if (Objects.nonNull(memberRequestVm.getId())) {
            throw new BadRequestException("id must be null");
        }
        if (!memberRequestVm.getMemberShipEndDate().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("memberShipEndDate must be after now");
        }
        Member member = MemberMapper.INSTANCE.toMember(memberRequestVm);
        member = memberRepo.save(member);
        return MemberMapper.INSTANCE.toMemberDto(member);
    }

    @Override
    public MembersResponseVm getMembers(int page, int size) {
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<Member> result = memberRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("members not found");
        }
        return new MembersResponseVm(result.map(MemberMapper.INSTANCE::toMemberDto).getContent(), result.getTotalElements());
    }

    @Override
    public MemberDto getMember(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Member> result = memberRepo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("member not found");
        }
        return MemberMapper.INSTANCE.toMemberDto(result.get());
    }

    @Transactional
    @Override
    public void deleteMember(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        // ensure exists
        getMember(id);
        memberRepo.deleteById(id);
    }

    @Override
    public MemberDto updateMember(MemberRequestVm memberRequestVm) {
        if (Objects.isNull(memberRequestVm.getId())) {
            throw new BadRequestException("id must be not null");
        }
        MemberDto oldMemberDto = getMember(memberRequestVm.getId());
        Member oldMember = MemberMapper.INSTANCE.toMember(oldMemberDto);
        boolean update = false;
        update = updateData(memberRequestVm, oldMember, update);
        if (update) {
            oldMember = memberRepo.save(oldMember);
            return MemberMapper.INSTANCE.toMemberDto(oldMember);
        }
        throw new NotFoundResourceException("data must be different");
    }

    private static boolean updateData(MemberRequestVm memberRequestVm, Member oldMember, boolean update) {
        if (!oldMember.getFullName().equals(memberRequestVm.getFullName())) {
            update = true;
            oldMember.setFullName(memberRequestVm.getFullName());
        }
        if (!oldMember.getEmail().equals(memberRequestVm.getEmail())) {
            update = true;
            oldMember.setEmail(memberRequestVm.getEmail());
        }
        if (!oldMember.getPhone().equals(memberRequestVm.getPhone())) {
            update = true;
            oldMember.setPhone(memberRequestVm.getPhone());
        }
        if (!oldMember.getAddress().equals(memberRequestVm.getAddress())) {
            update = true;
            oldMember.setAddress(memberRequestVm.getAddress());
        }
        if (!oldMember.getMemberShipEndDate().equals(memberRequestVm.getMemberShipEndDate())) {
            update = true;
            oldMember.setMemberShipEndDate(memberRequestVm.getMemberShipEndDate());
        }
        return update;
    }
}
