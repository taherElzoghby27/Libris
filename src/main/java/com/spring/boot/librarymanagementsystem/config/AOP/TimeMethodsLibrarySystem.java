package com.spring.boot.librarymanagementsystem.config.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMethodsLibrarySystem {

    @Around("execution(* com.spring.boot.librarymanagementsystem.controller.*.*(..))")
    public Object aroundTimeMethodController(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        // get begin timestamp
        long begin = System.currentTimeMillis();
        // now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();
        // get end timestamp
        long end = System.currentTimeMillis();
        // compute duration and display it
        long duration = end - begin;
        System.out.println("controller =====> Duration: " + duration + " ms " + theProceedingJoinPoint.getSignature().getName());
        return result;
    }

    @Around("execution(* com.spring.boot.librarymanagementsystem.service.implementation.*.*(..))")
    public Object aroundTimeMethodService(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        // get begin timestamp
        long begin = System.currentTimeMillis();
        // now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();
        // get end timestamp
        long end = System.currentTimeMillis();
        // compute duration and display it
        long duration = end - begin;
        System.out.println("service =====> Duration: " + duration + " ms " + theProceedingJoinPoint.getSignature().getName());
        return result;
    }
}