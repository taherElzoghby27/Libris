package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.entity.Member;
import com.spring.boot.librarymanagementsystem.vm.member.MemberRequestVm;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    Member toMember(MemberRequestVm vm);

    MemberDto toMemberDto(Member member);

    Member toMember(MemberDto memberDto);

    @Named("memberToId")
    default Long memberToId(Member member) {
        return member != null ? member.getId() : null;
    }
}
