package com.example.online.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.online.model.FoodOrder;

@Aspect
@Component	
public class FoodOrderAudit {

    // BEFORE ADVICE
    @Before("execution(* com.example.online.service.FoodOrderService.addOrder(..))")
    public void logBeforeOrder(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        FoodOrder order = (FoodOrder) args[0];

        System.out.println("----- New Order Started -----");
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Food Item: " + order.getFoodItem());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Price: Rs. " + order.getPrice());
    }


    // AFTER RETURNING ADVICE
    @AfterReturning(
        pointcut = "execution(* com.example.online.service.FoodOrderService.addOrder(..))",
        returning = "result"
    )
    public void logOrderSuccess(JoinPoint joinPoint, FoodOrder result) {

        System.out.println("----- Order Confirmation -----");
        System.out.println("Order ID: " + result.getOrderId());
        System.out.println("Customer Name: " + result.getCustomerName());
        System.out.println("Food Item: " + result.getFoodItem());
        System.out.println("Total Amount: Rs. " +
                (result.getQuantity() * result.getPrice()));
        System.out.println("Order placed successfully!");
    }


    // AFTER THROWING ADVICE
    @AfterThrowing(
        pointcut = "execution(* com.example.online.service.FoodOrderService.*(..))",
        throwing = "ex"
    )
    public void logOrderFailure(JoinPoint joinPoint, Exception ex) {

        System.out.println("----- Order Operation Failed -----");
        System.out.println("Method: " +
                joinPoint.getSignature().getName());
        System.out.println("Reason: " + ex.getMessage());
    }


    // AROUND ADVICE
    @Around("execution(* com.example.online.service.FoodOrderService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long startTime = System.currentTimeMillis();

        System.out.println("----- Method Execution Started -----");
        System.out.println("Method: " +
                joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        System.out.println("Execution Time: " +
                (endTime - startTime) + " ms");

        System.out.println("----- Method Execution Completed -----");

        return result;
    }
}