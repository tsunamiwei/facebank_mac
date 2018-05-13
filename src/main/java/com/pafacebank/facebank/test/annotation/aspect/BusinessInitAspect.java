package com.pafacebank.facebank.test.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BusinessInitAspect {

    @Around("@annotation(com.pafacebank.facebank.test.annotation.BusinessInit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("BusinessInit: " + joinPoint.getSignature());
        return joinPoint.proceed(joinPoint.getArgs());
    }

}
