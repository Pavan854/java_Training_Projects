
package com.example.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.student.model.Student;

@Aspect
@Component
public class StudentAudit {

    // BEFORE ADVICE
    @Before("execution(* com.example.student.service.StudentService.addStudent(..))")
    public void logBeforeStudent(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Student student = (Student) args[0];

        System.out.println("----- New Student Registration Started -----");

        System.out.println("Student Name: " + student.getStudentName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Course: " + student.getCourse());
        System.out.println("Course Fee: Rs. " + student.getFee());
    }

    // AFTER RETURNING ADVICE
    @AfterReturning(
        pointcut = "execution(* com.example.student.service.StudentService.addStudent(..))",
        returning = "result"
    )
    public void logStudentSuccess(JoinPoint joinPoint, Student result) {

        System.out.println("----- Student Registration Confirmation -----");

        System.out.println("Student ID: " + result.getStudentId());
        System.out.println("Student Name: " + result.getStudentName());
        System.out.println("Course: " + result.getCourse());
        System.out.println("Enrollment Status: " +
                result.getEnrollmentStatus());

        System.out.println("Student registered successfully!");
    }

    // AFTER THROWING ADVICE
    @AfterThrowing(
        pointcut = "execution(* com.example.student.service.StudentService.*(..))",
        throwing = "ex"
    )
    public void logStudentFailure(JoinPoint joinPoint, Exception ex) {

        System.out.println("----- Student Operation Failed -----");

        System.out.println("Method: " +
                joinPoint.getSignature().getName());

        System.out.println("Reason: " + ex.getMessage());
    }

    // AROUND ADVICE
    @Around("execution(* com.example.student.service.StudentService.*(..))")
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
