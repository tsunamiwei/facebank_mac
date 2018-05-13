package com.pafacebank.facebank.test.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BusinessProcessAspect {
    @Around("@within(com.pafacebank.facebank.test.annotation.BusinessProcess)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("BusinessProcess: " + joinPoint.getSignature());
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
