package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        logger.info("Starting method: {}", joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        logger.info("Method: {} executed in {}ms",
                joinPoint.getSignature().getName(), (end - start));

        return result;
    }
}