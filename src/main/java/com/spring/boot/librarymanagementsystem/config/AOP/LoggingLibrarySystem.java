package com.spring.boot.librarymanagementsystem.config.AOP;

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
    public void afterReturning(JoinPoint joinPoint, Object result) {
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
}
