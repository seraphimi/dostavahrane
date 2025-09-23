package com.dostavahrane.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    @Autowired
    private ApiLogger apiLogger;
    
    @Pointcut("execution(* com.dostavahrane.controller.*.*(..))")
    public void controllerMethods() {}
    
    @After("controllerMethods()")
    public void logAfterApiCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        
        apiLogger.logApiCall(methodName, className, args);
    }
    
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterApiReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        
        apiLogger.logApiResult(methodName, className, result);
    }
}