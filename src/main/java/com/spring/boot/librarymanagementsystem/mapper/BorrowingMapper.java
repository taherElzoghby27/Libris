package com.spring.boot.librarymanagementsystem.mapper;

import com.spring.boot.librarymanagementsystem.dto.BorrowingDto;
import com.spring.boot.librarymanagementsystem.entity.Borrowing;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingRequestVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingResponseVm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BorrowingMapper {
    BorrowingMapper INSTANCE = Mappers.getMapper(BorrowingMapper.class);

    @Mapping(target = "books", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "issuedByUser", ignore = true)
    @Mapping(target = "returnedByUser", ignore = true)
    Borrowing toBorrowing(BorrowingRequestVm vm);

    BorrowingDto toBorrowingDto(Borrowing borrowing);

    BorrowingResponseVm toBorrowingResponseVm(Borrowing borrowing);

    Borrowing toBorrowing(BorrowingDto borrowingDto);
}
