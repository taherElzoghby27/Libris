package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.entity.Member;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.MemberMapper;
import com.spring.boot.librarymanagementsystem.repository.MemberRepo;
import com.spring.boot.librarymanagementsystem.service.MemberService;
import com.spring.boot.librarymanagementsystem.service.PaginationService;
import com.spring.boot.librarymanagementsystem.utils.MemberShipStatus;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestVm;
import com.spring.boot.librarymanagementsystem.vm.member.MembersResponseVm;
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
        if (!memberRequestVm.getMemberShipEndDate().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("memberShipEndDate must be after now");
        }
        Member member = MemberMapper.INSTANCE.toMember(memberRequestVm);
        member.setMemberShipStatus(MemberShipStatus.ACTIVE);
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
    public MemberDto updateMember(MemberRequestUpdateVm memberRequestUpdateVm) {
        MemberDto oldMemberDto = getMember(memberRequestUpdateVm.getId());
        Member oldMember = MemberMapper.INSTANCE.toMember(oldMemberDto);
        boolean update = false;
        update = updateData(memberRequestUpdateVm, oldMember, update);
        if (update) {
            oldMember = memberRepo.save(oldMember);
            return MemberMapper.INSTANCE.toMemberDto(oldMember);
        }
        throw new BadRequestException("data must be different");
    }

    private static boolean updateData(MemberRequestUpdateVm memberRequestUpdateVm, Member oldMember, boolean update) {
        if (Objects.nonNull(memberRequestUpdateVm.getFullName()) &&
            !oldMember.getFullName().equals(memberRequestUpdateVm.getFullName())) {
            update = true;
            oldMember.setFullName(memberRequestUpdateVm.getFullName());
        }
        if (Objects.nonNull(memberRequestUpdateVm.getAddress()) &&
            !oldMember.getEmail().equals(memberRequestUpdateVm.getEmail())) {
            update = true;
            oldMember.setEmail(memberRequestUpdateVm.getEmail());
        }
        if (Objects.nonNull(memberRequestUpdateVm.getPhone()) &&
            !oldMember.getPhone().equals(memberRequestUpdateVm.getPhone())) {
            update = true;
            oldMember.setPhone(memberRequestUpdateVm.getPhone());
        }
        if (Objects.nonNull(memberRequestUpdateVm.getAddress()) &&
            !oldMember.getAddress().equals(memberRequestUpdateVm.getAddress())) {
            update = true;
            oldMember.setAddress(memberRequestUpdateVm.getAddress());
        }
        if (Objects.nonNull(memberRequestUpdateVm.getMemberShipEndDate()) &&
            !memberRequestUpdateVm.getMemberShipEndDate().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("memberShipEndDate must be after now");
        }
        if (Objects.nonNull(memberRequestUpdateVm.getMemberShipEndDate()) &&
            !oldMember.getMemberShipEndDate().equals(memberRequestUpdateVm.getMemberShipEndDate())) {
            update = true;
            oldMember.setMemberShipEndDate(memberRequestUpdateVm.getMemberShipEndDate());
        }
        if (!oldMember.getMemberShipStatus().equals(memberRequestUpdateVm.getMemberShipStatus())) {
            update = true;
            oldMember.setMemberShipStatus(memberRequestUpdateVm.getMemberShipStatus());
        }
        return update;
    }
}
