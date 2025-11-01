package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.dto.BorrowingDto;
import com.spring.boot.librarymanagementsystem.dto.MemberDto;
import com.spring.boot.librarymanagementsystem.dto.UserSystemDto;
import com.spring.boot.librarymanagementsystem.entity.Book;
import com.spring.boot.librarymanagementsystem.entity.Borrowing;
import com.spring.boot.librarymanagementsystem.entity.Member;
import com.spring.boot.librarymanagementsystem.entity.UserSystem;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.BookMapper;
import com.spring.boot.librarymanagementsystem.mapper.BorrowingMapper;
import com.spring.boot.librarymanagementsystem.mapper.MemberMapper;
import com.spring.boot.librarymanagementsystem.mapper.UserMapper;
import com.spring.boot.librarymanagementsystem.repository.BorrowingRepo;
import com.spring.boot.librarymanagementsystem.service.*;
import com.spring.boot.librarymanagementsystem.utils.enums.BorrowingStatus;
import com.spring.boot.librarymanagementsystem.vm.activity.ActivityRequestVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingRequestVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingResponseVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.borrowing.BorrowingsResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepo borrowingRepo;
    private final BookService bookService;
    private final MemberService memberService;
    private final UserService userService;
    private final ActivityUserService activityUserService;

    @Transactional
    @Override
    public BorrowingDto addBorrowing(BorrowingRequestVm borrowingRequestVm) {
        validateDates(borrowingRequestVm.getIssuedAt(), borrowingRequestVm.getDueDate(), borrowingRequestVm.getReturnedAt());
        Borrowing borrowing = BorrowingMapper.INSTANCE.toBorrowing(borrowingRequestVm);
        // set relations
        setRelations(borrowingRequestVm, borrowing);
        borrowing.setBorrowingStatus(BorrowingStatus.BORROWED);
        borrowing = borrowingRepo.save(borrowing);
        //add log
        activityUserService.addActivity(new ActivityRequestVm(
                        "add borrowing book",
                        "Borrowing",
                        borrowing.getBook() + " borrowed by " + borrowing.getMember().getFullName()
                )
        );
        return BorrowingMapper.INSTANCE.toBorrowingDto(borrowing);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    protected void setRelations(BorrowingRequestVm vm, Borrowing borrowing) {
        // book
        BookDto bookDto = bookService.getBookById(vm.getBookId());
        Book book = BookMapper.INSTANCE.toBook(bookDto);
        borrowing.setBook(book);
        // member
        MemberDto memberDto = memberService.getMember(vm.getMember());
        Member member = MemberMapper.INSTANCE.toMember(memberDto);
        borrowing.setMember(member);
        // issued by user
        UserSystemDto issuedByUserDto = userService.getUserById(vm.getIssuedByUserId());
        UserSystem issuedByUser = UserMapper.INSTANCE.toUserSystem(issuedByUserDto);
        borrowing.setIssuedByUser(issuedByUser);
        // returned by user
        if (Objects.nonNull(vm.getReturnedByUser())) {
            UserSystemDto returnedByUserDto = userService.getUserById(vm.getReturnedByUser());
            UserSystem returnedByUser = UserMapper.INSTANCE.toUserSystem(returnedByUserDto);
            borrowing.setReturnedByUser(returnedByUser);
        }
    }

    private void validateDates(LocalDateTime issuedAt, LocalDateTime dueDate, LocalDateTime returnedAt) {
        if (!dueDate.isAfter(issuedAt)) {
            throw new BadRequestException("dueDate must be after issuedAt");
        }
        if (Objects.nonNull(returnedAt) && returnedAt.isBefore(issuedAt)) {
            throw new BadRequestException("returnedAt must be after issuedAt");
        }
    }

    @Override
    public BorrowingResponseVm getBorrowingWithoutData(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Borrowing> result = borrowingRepo.findBorrowing(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("borrowing not found");
        }
        return BorrowingMapper.INSTANCE.toBorrowingResponseVm(result.get());
    }

    @Override
    public BorrowingDto getBorrowingWithData(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Borrowing> result = borrowingRepo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("borrowing not found");
        }
        return BorrowingMapper.INSTANCE.toBorrowingDto(result.get());
    }

    @Override
    public BorrowingsResponseVm getAllBorrowingsWithData(int page, int size) {
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<Borrowing> result = borrowingRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("borrowings not found");
        }
        return new BorrowingsResponseVm(
                result.map(BorrowingMapper.INSTANCE::toBorrowingResponseVm).getContent(),
                result.getTotalElements()
        );
    }

    @Transactional
    @Override
    public BorrowingDto updateBorrowing(BorrowingUpdateVm borrowingUpdateVm) {
        validateDates(borrowingUpdateVm.getIssuedAt(), borrowingUpdateVm.getDueDate(), borrowingUpdateVm.getReturnedAt());
        // ensure exists
        BorrowingDto oldDto = getBorrowingWithData(borrowingUpdateVm.getId());
        Borrowing oldBorrowing = BorrowingMapper.INSTANCE.toBorrowing(oldDto);
        boolean update = false;
        update = updateData(borrowingUpdateVm, oldBorrowing, update);
        if (update) {
            oldBorrowing = borrowingRepo.save(oldBorrowing);
            //add log
            activityUserService.addActivity(new ActivityRequestVm(
                            "update borrowing book",
                            "Borrowing",
                            oldBorrowing.getBook() + " borrowed by " + oldBorrowing.getMember().getFullName() + " status : " + oldBorrowing.getBorrowingStatus()
                    )
            );
            return BorrowingMapper.INSTANCE.toBorrowingDto(oldBorrowing);
        }
        throw new BadRequestException("data must be different");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    protected boolean updateData(BorrowingUpdateVm borrowingUpdateVm, Borrowing oldBorrowing, boolean update) {
        if (Objects.nonNull(borrowingUpdateVm.getIssuedAt()) &&
            !oldBorrowing.getIssuedAt().equals(borrowingUpdateVm.getIssuedAt())) {
            update = true;
            oldBorrowing.setIssuedAt(borrowingUpdateVm.getIssuedAt());
        }
        if (Objects.nonNull(borrowingUpdateVm.getDueDate()) &&
            !oldBorrowing.getDueDate().equals(borrowingUpdateVm.getDueDate())) {
            update = true;
            oldBorrowing.setDueDate(borrowingUpdateVm.getDueDate());
        }
        if (Objects.nonNull(borrowingUpdateVm.getReturnedAt()) &&
            !borrowingUpdateVm.getReturnedAt().equals(oldBorrowing.getReturnedAt())) {
            update = true;
            oldBorrowing.setReturnedAt(borrowingUpdateVm.getReturnedAt());
        }
        if (Objects.nonNull(borrowingUpdateVm.getBorrowingStatus()) &&
            !oldBorrowing.getBorrowingStatus().equals(borrowingUpdateVm.getBorrowingStatus())) {
            update = true;
            oldBorrowing.setBorrowingStatus(borrowingUpdateVm.getBorrowingStatus());
        }
        if (Objects.nonNull(borrowingUpdateVm.getBookId())) {
            BookDto bookDto = bookService.getBookById(borrowingUpdateVm.getBookId());
            oldBorrowing.setBook(BookMapper.INSTANCE.toBook(bookDto));
            update = true;
        }
        if (Objects.nonNull(borrowingUpdateVm.getIssuedByUserId())) {
            UserSystemDto userSystemDto = userService.getUserById(borrowingUpdateVm.getIssuedByUserId());
            oldBorrowing.setIssuedByUser(UserMapper.INSTANCE.toUserSystem(userSystemDto));
            update = true;
        }
        if (Objects.nonNull(borrowingUpdateVm.getReturnedByUser())) {
            UserSystemDto userSystemDto = userService.getUserById(borrowingUpdateVm.getReturnedByUser());
            oldBorrowing.setReturnedByUser(UserMapper.INSTANCE.toUserSystem(userSystemDto));
            update = true;
        }
        if (oldBorrowing.getBorrowingStatus().equals(BorrowingStatus.RETURNED) && oldBorrowing.getReturnedByUser() == null) {
            throw new BadRequestException("returnedByUser must be not null");
        }
        if (!(oldBorrowing.getReturnedAt().isAfter(oldBorrowing.getIssuedAt()) &&
              oldBorrowing.getReturnedAt().isBefore(oldBorrowing.getDueDate()))) {
            throw new BadRequestException("returned at must be after issued at and returned at must be before due date at");
        }
        return update;
    }

    @Transactional
    @Override
    public void deleteBorrowing(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        // ensure exists
        BorrowingResponseVm borrowingResponseVm = getBorrowingWithoutData(id);
        borrowingRepo.deleteById(id);
        //add log
        activityUserService.addActivity(new ActivityRequestVm(
                        "delete borrowing book",
                        "Borrowing",
                        borrowingResponseVm.getBorrowingStatus().toString()
                )
        );
    }
}
