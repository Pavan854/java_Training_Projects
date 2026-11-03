
package com.example.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.book.model.Library;

@Aspect
@Component
public class LibraryAudit {

    // BEFORE ADVICE
    @Before("execution(* com.example.book.service.LibraryService.addBook(..))")
    public void logBeforeBook(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        Library book = (Library) args[0];

        System.out.println("----- New Book Addition Started -----");
        System.out.println("Book Name: " + book.getBookName());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: Rs. " + book.getPrice());
        System.out.println("Total Copies: " + book.getTotalCopies());
    }


    // AFTER RETURNING ADVICE
    @AfterReturning(
        pointcut = "execution(* com.example.book.service.LibraryService.addBook(..))",
        returning = "result"
    )
    public void logBookSuccess(JoinPoint joinPoint, Library result) {

        System.out.println("----- Book Addition Confirmation -----");
        System.out.println("Book ID: " + result.getBookId());
        System.out.println("Book Name: " + result.getBookName());
        System.out.println("Author: " + result.getAuthor());
        System.out.println("Availability: " + result.getAvailability());
        System.out.println("Book added successfully!");
    }


    // AFTER THROWING ADVICE
    @AfterThrowing(
        pointcut = "execution(* com.example.book.service.LibraryService.*(..))",
        throwing = "ex"
    )
    public void logBookFailure(JoinPoint joinPoint, Exception ex) {

        System.out.println("----- Book Operation Failed -----");
        System.out.println("Method: " +
                joinPoint.getSignature().getName());
        System.out.println("Reason: " + ex.getMessage());
    }


    // AROUND ADVICE
    @Around("execution(* com.example.book.service.LibraryService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long startTime = System.currentTimeMillis();

        System.out.println("----- Method Execution Started -----");
        System.out.println("Method: " +
                joinPoint.getSignature().getName());

        try {
            return joinPoint.proceed();
        } finally {

            long endTime = System.currentTimeMillis();

            System.out.println("Execution Time: " +
                    (endTime - startTime) + " ms");

            System.out.println("----- Method Execution Completed -----");
        }
    }
}
