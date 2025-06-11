package com.example.soteria.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.soteria.service.AuthenticationService.authenticate(..))")
    public void logAuthenticationAttempt(JoinPoint joinPoint) {
        logger.info("Authentication attempt: {}", joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* com.example.soteria.service.AuthenticationService.authenticate(..))", returning = "result")
    public void logAuthenticationOutcome(JoinPoint joinPoint, Object result) {
        logger.info("Authentication outcome for {}: {}", joinPoint.getArgs(), result != null ? "successful" : "failed");
    }

    @AfterThrowing(pointcut = "execution(* com.example.soteria.service.AuthenticationService.authenticate(..))", throwing = "exception")
    public void logAuthenticationFailure(JoinPoint joinPoint, Throwable exception) {
        logger.error("Authentication failed for: {}. Exception: {}", joinPoint.getArgs(), exception.getMessage());
    }
}