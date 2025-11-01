package com.spring.boot.librarymanagementsystem.config.AOP;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.dto.BookDto;
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
}
