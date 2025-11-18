package com.project.eCommerce.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all methods in Service package
    @Pointcut("execution(* com.project.eCommerce.Service.*.*(..))")
    public void serviceMethods() {}

    // Before advice
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before executing: {}", joinPoint.getSignature());
    }

    // AfterReturning advice
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method executed: {} | Returned: {}", joinPoint.getSignature(), result);
    }

    // AfterThrowing advice
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method: {} | Exception: {}", joinPoint.getSignature(), ex.getMessage());
    }

    // Around advice
    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        logger.info("Starting method: {}", pjp.getSignature());
        Object result = pjp.proceed();
        long duration = System.currentTimeMillis() - start;
        logger.info("Completed method: {} | Execution time: {} ms", pjp.getSignature(), duration);
        return result;
    }
}
