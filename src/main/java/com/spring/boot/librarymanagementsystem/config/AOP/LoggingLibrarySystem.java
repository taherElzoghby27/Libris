package com.spring.boot.librarymanagementsystem.config.AOP;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.dto.BorrowingDto;
import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.vm.user.UserResponseVm;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingLibrarySystem {
    //for book logging (add, remove, update)
    @AfterReturning(
            pointcut = "execution(* com.spring.boot.librarymanagementsystem.service.implementation.BookServiceImpl.*Book(..))",
            returning = "result"
    )
    public void afterReturningBook(JoinPoint joinPoint, Object result) {
        BookDto bookDto = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (result instanceof BookDto) {
            bookDto = (BookDto) result;
        }
        System.out.println("Book Operation : " + signature.getMethod().getName());
        if (bookDto != null) {
            System.out.println("--> " + bookDto.getTitle() + ", " + bookDto.getPublisher().getName());
        }
    }

    //for author logging (add, remove, update)
    @AfterReturning(
            pointcut = "execution(* com.spring.boot.librarymanagementsystem.service.implementation.AuthorServiceImpl.*Author(..))",
            returning = "result"
    )
    public void afterReturningAuthor(JoinPoint joinPoint, Object result) {
        AuthorDto authorDto = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (result instanceof AuthorDto) {
            authorDto = (AuthorDto) result;
        }
        System.out.println("Author Operation : " + signature.getMethod().getName());
        if (authorDto != null) {
            System.out.println("--> " + authorDto.getFirstName() + authorDto.getLastName() + ", " + authorDto.getBio());
        }
    }

    //for auth logging
    @AfterReturning(
            pointcut = "execution(* com.spring.boot.librarymanagementsystem.service.implementation.AuthServiceImpl.*(..))",
            returning = "result"
    )
    public void afterReturningAuth(JoinPoint joinPoint, Object result) {
        UserResponseVm authResponseVm = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (result instanceof UserResponseVm) {
            authResponseVm = (UserResponseVm) result;
        }
        System.out.println("Auth Operation : " + signature.getMethod().getName());
        if (authResponseVm != null) {
            System.out.println("--> " + authResponseVm.getEmail() + ", " + authResponseVm.getUsername());
        }
    }

    //for borrowing logging (add, remove, update)
    @AfterReturning(
            pointcut = "execution(* com.spring.boot.librarymanagementsystem.service.implementation.BorrowingServiceImpl.*Borrowing(..))",
            returning = "result"
    )
    public void afterReturningBorrowing(JoinPoint joinPoint, Object result) {
        BorrowingDto borrowingDto = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (result instanceof BorrowingDto) {
            borrowingDto = (BorrowingDto) result;
        }
        System.out.println("Borrowing Operation : " + signature.getMethod().getName());
        if (borrowingDto != null) {
            System.out.println("--> " + borrowingDto.getBook().getTitle() + ", issued by : " + borrowingDto.getIssuedByUser().getUsername());
            System.out.println("--> returned by : " + borrowingDto.getReturnedByUser().getUsername() + ", member : " + borrowingDto.getMember().getFullName());
        }
    }
    //for category logging (add, remove, update)
    @AfterReturning(
            pointcut = "execution(* com.spring.boot.librarymanagementsystem.service.implementation.CategoryServiceImpl.*Category(..))",
            returning = "result"
    )
    public void afterReturningCategory(JoinPoint joinPoint, Object result) {
        CategoryDto categoryDto = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (result instanceof CategoryDto) {
            categoryDto = (CategoryDto) result;
        }
        System.out.println("Category Operation : " + signature.getMethod().getName());
        if (categoryDto != null) {
            System.out.println("--> category name : " + categoryDto.getName());
            System.out.println("--> category parent : " + categoryDto.getParent().getName());
        }
    }
}
